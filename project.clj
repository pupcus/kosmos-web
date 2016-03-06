(defproject kosmos/kosmos-web "0.0.3-SNAPSHOT"

  :description "simple ring web server component"

  :url "https://bitbucket.org/pupcus/kosmos-web"

  :scm {:url "git@bitbucket.org:bitbucket/kosmos-web"}

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[kosmos "0.0.3"]
                 [ring/ring-jetty-adapter "1.4.0"]]

  :profiles {:dev {:resource-paths ["dev-resources"]
                   :dependencies [[org.clojure/clojure "1.8.0"]
                                  [org.clojure/tools.reader "1.0.0-alpha3"]
                                  [clj-http "2.1.0"]
                                  [org.eclipse.jetty/jetty-servlets "9.2.10.v20150310"]
                                  [org.eclipse.jetty/jetty-xml "9.2.10.v20150310"]]}}

  :test-selectors {:default (complement (some-fn :integration))
                   :integration :integration
                   :all (fn [m] true)}

  :deploy-repositories [["snapshots"
                         {:url "https://clojars.org/repo"
                          :creds :gpg}]
                        ["releases"
                         {:url "https://clojars.org/repo"
                          :creds :gpg}]])
