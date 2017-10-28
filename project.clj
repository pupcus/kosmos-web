(defproject kosmos/kosmos-web "0.0.4-SNAPSHOT"

  :description "simple ring web server component"

  :url "https://bitbucket.org/pupcus/kosmos-web"

  :scm {:url "git@bitbucket.org:bitbucket/kosmos-web"}

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[kosmos "0.0.6"]
                 [ring/ring-jetty-adapter "1.6.2"]]

  :profiles {:dev {:resource-paths ["dev-resources"]
                   :dependencies [[org.clojure/clojure "1.8.0"]
                                  [org.clojure/tools.reader "1.0.0-beta4"]
                                  [clj-http "2.2.0"]
                                  [org.eclipse.jetty/jetty-servlets "9.2.21.v20170120"]
                                  [org.eclipse.jetty/jetty-xml "9.2.21.v20170120"]]}}

  :test-selectors {:default (complement (some-fn :integration))
                   :integration :integration
                   :all (fn [m] true)}

  :deploy-repositories [["snapshots"
                         {:url "https://clojars.org/repo"
                          :sign-releases false
                          :creds :gpg}]
                        ["releases"
                         {:url "https://clojars.org/repo"
                          :sign-releases false
                          :creds :gpg}]]

  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]])
