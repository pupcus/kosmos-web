# kosmos-web

A Kosmos component for starting a web server. Provides a RingJettyComponent that uses Ring's Jetty adapter.

## Example Configuration 

```clj
   {:web 
     {:kosmos/init kosmos.web/RingJettyComponent
      :port 1111
      :ring-app some-ring-app
      :app-init init-fn}}
```

The `some-ring-app` is the app var itself. The `init-fn` is a user-supplied function that does any initialization the Ring app such as setting up middleware.

FIXME

## License

Kosmos is distributed under the [Eclipse Public License](http://opensource.org/licenses/eclipse-1.0.php), the same as Clojure.
