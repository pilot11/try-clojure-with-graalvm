(defproject account-demo "1.0.0-SNAPSHOT"
  :description  "账号注册服务的一个demo"
  :source-paths ["src/clj" "src/java"]
  :java-source-paths ["src/java"]
  :resource-paths ["etc"]
  ;:aot :all
  :plugins [[io.taylorwood/lein-native-image "0.3.0"]]
  :main account.main
  :native-image {:name "account-demo"
                 :graal-bin "/Library/Java/JavaVirtualMachines/graalvm-ce-1.0.0-rc6/Contents/Home/bin"
                 :opts ["-Dclojure.compiler.direct-linking=true"
                        "-H:EnableURLProtocols=http"]}

  :dependencies [[org.clojure/clojure "1.9.0"]              ;clojure核心
                 [cheshire "5.8.0"]                         ;json库
                 [ring "1.6.3"]                             ;web框架
                 [ring/ring-json "0.4.0"]                   ;ring的json处理中间件
                 [bk/ring-gzip "0.3.0"]                     ;ring的压缩处理中间件
                 [bidi "2.1.3"]                             ;路由
                 [clj-http "3.7.0"]]
  :repositories [["clojars" {:url "https://repo.clojars.org/"}]]
  )
