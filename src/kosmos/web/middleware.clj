(ns kosmos.web.middleware
  (:rquire [kosmos :refer [system]]))

;; WIP

(defn refresh-request [request timeout]
  (let [start (System/currentTimeMillis)]
    (loop []
      (Thread/sleep 1000)
      (cond
        system  request

        (> (- (System/currentTimeMillis) start) timout)
        :timeout

        :default (recur)))))

(defn wait-on-system-initialization [handler timeout]
  (fn [req]
    (if system
      (handler req)
      (let [result (refresh-request req timeout)]
        (if (= result :timeout)
          (response/redirect ())
          (recur request))))))ccccccevdgcguvjnehrhhvfrikvbkhgrtkvdkenihrev

