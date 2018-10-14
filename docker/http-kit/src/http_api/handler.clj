(ns http-api.handler
  (:require
   [clj-http.lite.client :as http]
   [clojure.java.io :as io]
   [compojure.core :refer :all]
   [compojure.route :as route]
   [ring.middleware.json :as json]
   [ring.util.response :as resp])
  (:import (java.time Instant)))

(defroutes my-routes
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
