(ns account.main
  "服务入口"
  (:require [account.db :as d]
            [account.http :as http]
            [account.register :as reg]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn start-server
  "启动服务"
  [cfg]
  (let [{:keys [web-opts datomic-url]} cfg
        conn (d/mk-conn datomic-url d/account-schema)
        context {:conn conn}
        handler (http/handler {:exist (reg/mk-exist context)
                               :register (reg/mk-register context)})]
    (println "start server. options : " web-opts)
    (run-jetty handler web-opts)))

(defn -main
  "服务程序入口"
  [& opts]
  (start-server {:web-opts {:host "0.0.0.0" :port 8080 :join? false}
                 :datomic-url "datomic:mem:db-name"})
  (when opts (System/exit 0)))
