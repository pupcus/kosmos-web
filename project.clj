(defproject kosmos/kosmos-web "0.0.6-SNAPSHOT"

  :description "simple ring web server component"

  :url "https://bitbucket.org/pupcus/kosmos-web"

  :scm {:url "git@bitbucket.org:bitbucket/kosmos-web"}

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[kosmos "0.0.10"]
                 [ring/ring-jetty-adapter "1.7.1" :exclude org.eclipse.jetty/jetty-server]
                 [org.eclipse.jetty/jetty-server "9.4.15.v20190215"]]

  :profiles {:dev {:resource-paths ["dev-resources"]
                   :dependencies [[org.clojure/clojure "1.10.1"]
                                  [clj-http "3.10.0"]
                                  [org.eclipse.jetty/jetty-servlets "9.4.15.v20190215"]
                                  [org.eclipse.jetty/jetty-xml "9.4.15.v20190215"]]}}

  :test-selectors {:default (complement (some-fn :integration))
                   :integration :integration
                   :all (fn [m] true)}



  :deploy-repositories {"releases" {:url "https://repo.clojars.org" :creds :gpg :sign-releases false}
                        "snapshots" {:url "https://repo.clojars.org" :creds :gpg :sign-releases false}}


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
