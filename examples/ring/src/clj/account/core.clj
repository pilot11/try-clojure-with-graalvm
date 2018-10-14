(ns account.core
  (:require [account.db :as d]))

(defn username-len-valid?
  "用户名长度是否有效"
  [username]
  (<= 6 (count username) 20))

(defn username-exist?
  "用户名是否存在"
  [db username]
  (-> (d/find-account db username)
      :account/username
      boolean))
