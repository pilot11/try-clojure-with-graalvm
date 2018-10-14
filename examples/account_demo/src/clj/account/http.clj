(ns account.http
  "http服务接入"
  (:require [ring.middleware.gzip :refer [wrap-gzip]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.json :refer [wrap-json-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [bidi.ring :refer [make-handler]]
            [cheshire.core :as json]))

(defn routes
  "路由"
  [{:keys [exist register]}]
  ["/"
   {:get {["api/call"]              (fn [_] "ok")
          ["api/exist/" :username]  (fn [{:keys [params]}] (exist params))}
    :post {["api/reg"]              (fn [{:keys [params] :as req}]
                                      (clojure.pprint/pprint req)
                                      (register params))}}])

(defn wrap-resp
  "包装响应内容"
  [handler]
  (fn [req]
    {:status  200
     :body    (json/generate-string (handler req))
     :headers {"Content-Type" "application/json; charset=utf-8"}}))

(defn handler
  [fns]
  (-> (routes fns)
      make-handler
      wrap-keyword-params
      wrap-json-params
      wrap-resp
      (wrap-resource "public")
      wrap-gzip))

(comment
  (require '[ring.adapter.jetty :refer [run-jetty]]
           '[clj-http.client :as client])
  (def server
    (run-jetty (handler {:exist identity :register identity})
               {:host "0.0.0.0" :port 8080 :join? false}))
  (.stop server)

  (client/get "http://127.0.0.1:8080/api/exist/foo")
  (client/post "http://127.0.0.1:8080/api/reg"
               {:headers {"Content-Type" "application/json; charset=utf-8"}
                :body (json/generate-string {:username "foo"
                                             :password "123456"})})
  )
