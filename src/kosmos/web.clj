(ns kosmos.web
  (:require [clojure.tools.logging :as log]
            [clojure.java.io :as io]
            [com.stuartsierra.component :as component]
            [kosmos.util :refer [resolve-symbol]]
            [ring.adapter.jetty :refer [run-jetty]]))

;; TODO: ?? macro in kosmos core util.clj ??
(defn expect-keys [m & ks]
  (when-not (every? (partial contains? m) ks)
    (let [error-msg (format "You must supply %s entries before starting the RingJettyComponent." (pr-str ks))]
      (throw
       (ex-info
        error-msg
        {:map m
         :keys ks}
        (IllegalArgumentException. error-msg))))))

(defn- setup-app-var [{:keys [ring-app app-init] :as component}]
  (log/info "initializing web app handler")
  (if app-init
    (let [my-app (resolve-symbol ring-app)]
      (alter-var-root
       my-app
       (resolve-symbol app-init)
       component)
      my-app)
    (resolve-symbol ring-app)))

(defrecord RingJettyComponent []
  component/Lifecycle
  (start [{:keys [configurator] :as component}]
    (log/info "Starting Ring-based Jetty server component.")
    (expect-keys component :ring-app :port)
    (let [app-var      (setup-app-var component)
          component    (assoc component
                              :configurator
                              (when configurator
                                (resolve-symbol configurator)))]
      (assoc component :server (run-jetty app-var (into {} component)))))

  (stop [{:keys [server] :as component}]
    (log/info "Stopping Ring-based Jetty server component.")
    (when server
      (.stop ^org.eclipse.jetty.server.Server server))
    (dissoc component :server)))
