{:dev {:source-paths ["src/dev" "src/clj" "src/cljs"]
       :plugins      [[lein-figwheel "0.5.8" :exclusions [org.clojure/clojure org.clojure/core.async]]
                      [lein-cooper "1.2.2" :exclusions [org.clojure/clojure]]]
       :dependencies [[figwheel-sidecar "0.5.8"]
                      [binaryage/devtools "0.8.2"]]}

 :uberjar {:aot          [starcity.core]
           :prep-tasks   ["compile" ["cljsbuild" "once"]]
           :source-paths ["src/clj" "src/cljs"]
           :cljsbuild
           {:builds [{:id           "admin"
                      :source-paths ["src/cljs/admin" "src/cljs/starcity"]
                      :jar          true
                      :compiler     {:main             admin.core
                                     :optimizations    :advanced
                                     :elide-asserts    true
                                     :pretty-print     false
                                     :parallel-build   true
                                     :asset-path       "/js/cljs/admin/out"
                                     :output-dir       "resources/public/js/cljs/admin/out"
                                     :output-to        "resources/public/js/cljs/admin.js"
                                     :closure-warnings {:externs-validation :off
                                                        :non-standard-jsdoc :off}}}
                     {:id           "apply"
                      :source-paths ["src/cljs/apply" "src/cljs/starcity"]
                      :jar          true
                      :compiler     {:main             apply.core
                                     :optimizations    :advanced
                                     :elide-asserts    true
                                     :pretty-print     false
                                     :parallel-build   true
                                     :asset-path       "/js/cljs/apply/out"
                                     :output-dir       "resources/public/js/cljs/apply/out"
                                     :output-to        "resources/public/js/cljs/apply.js"
                                     :closure-warnings {:externs-validation :off
                                                        :non-standard-jsdoc :off}}}
                     {:id           "mars"
                      :source-paths ["src/cljs/mars" "src/cljs/starcity"]
                      :jar          true
                      :compiler     {:main             mars.core
                                     :optimizations    :advanced
                                     :elide-asserts    true
                                     :pretty-print     false
                                     :parallel-build   true
                                     :asset-path       "/js/cljs/mars/out"
                                     :output-dir       "resources/public/js/cljs/mars/out"
                                     :output-to        "resources/public/js/cljs/mars.js"
                                     :closure-warnings {:externs-validation :off
                                                        :non-standard-jsdoc :off}}}]}}}
