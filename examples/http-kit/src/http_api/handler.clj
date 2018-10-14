(ns http-api.handler
  (:require
   [clj-http.lite.client :as http]
   [clojure.java.io :as io]
   [compojure.core :refer :all]
   [compojure.route :as route]
   [hickory.core :as hick]
   [ring.middleware.json :as json]
   [ring.util.response :as resp])
  (:import (java.time Instant)))

(defroutes my-routes
  (GET "/hick/:site" [site]
    (-> (http/get (str "http://" site))
        (:body)
        (hick/parse)
        (hick/as-hiccup)
        (pr-str)
        (resp/response)
        (resp/content-type "application/edn")))

  (GET "/call" []
   (-> (resp/response {:result "ok"})
       (resp/content-type "application/edn")))

  (GET "/freq/:site" [site]
    (resp/response
     {:frequencies (-> (http/get (str "http://" site))
                       (:body)
                       (frequencies))
      :timestamp (str (Instant/now))})))

(def app
  (routes
   (wrap-routes #'my-routes json/wrap-json-response)
   (route/not-found {:status 404 :body (slurp (io/resource "not-found.html"))})))
