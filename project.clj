(defproject kosmos/kosmos-web "0.0.8-SNAPSHOT"

  :description "simple ring web server component"

  :url "https://github.com/pupcus/kosmos-web"

  :scm {:url "git@github.com:pupcus/kosmos-web.git"}

  :signing {:gpg-key "FCA46A30FEEE7E10"}

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[kosmos "0.0.12"]
                 [ring/ring-jetty-adapter "1.8.2" :exclude org.eclipse.jetty/jetty-server]
                 [org.eclipse.jetty/jetty-server "9.4.31.v20200723"]]

  :profiles {:dev {:resource-paths ["dev-resources"]
                   :dependencies [[org.clojure/clojure "1.10.1"]
                                  [clj-http "3.11.0"]
                                  [org.eclipse.jetty/jetty-servlets "9.4.31.v20200723"]
                                  [org.eclipse.jetty/jetty-xml "9.4.31.v20200723"]]}}

  :test-selectors {:default (complement (some-fn :integration))
                   :integration :integration
                   :all (fn [m] true)}



  :deploy-repositories {"releases" {:url "https://clojars.org/repo" :creds :gpg :sign-releases false}
                        "snapshots" {:url "https://clojars.org/repo" :creds :gpg :sign-releases false}}


  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]

  :global-vars {*warn-on-reflection* true
                *assert* false})
