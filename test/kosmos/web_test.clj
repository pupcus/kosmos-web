(ns kosmos.web-test
  (:require [clj-http.client :as http]
            [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.test :refer :all]
            [com.stuartsierra.component :as component]
            [kosmos.web :refer :all])
  (:import [kosmos.web RingJettyComponent]))

(defn test-ring-app [request]
  {:status 200
   :headers {"Content-Type" "application/edn"}
   :body (pr-str {:testing 123})})

(def configurator-called (atom false))

(defn configurator [^org.eclipse.jetty.server.Server server]
  (reset! configurator-called true)
  (.setStopAtShutdown server true))

(def test-system
  {:web
   {:kosmos/init :kosmos.web/RingJettyComponent
    :join? false
    :ring-app #'test-ring-app
    :configurator #'configurator
    :port 1111}})

(def test-config (:web test-system))

(deftest test-component-ctor
  (let [component (map->RingJettyComponent test-config)]
    (is (instance? RingJettyComponent component))
    (is (every? (partial contains? component)
                [:ring-app :port]))))

(deftest ^:integration test-start-stop-component
  (let [component (component/start
                   (map->RingJettyComponent test-config))]
    (are [k v] (= (k component) v)
      :join? false
      :port 1111)
    (is (contains? component :ring-app))
    (is (true? @configurator-called) "configurator called")
    (let [response (http/get (str "http://localhost:" (:port component)))]
      (is (= {:testing 123}
             (edn/read-string (:body response))))
      (is (= 200
             (:status response))))
    (component/stop component)))
