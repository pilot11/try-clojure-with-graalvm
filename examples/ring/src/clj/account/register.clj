(ns account.register
  "注册业务流程"
  (:require [account.db :as d]
            [account.core :as core]))

(defn mk-exist
  "构造检查用户名是否被占用的处理函数"
  [context]
  (fn [{:keys [username]}]
    (let [db (d/conn->db (:conn context))]
      {:exist? (core/username-exist? db username)})))

(defn mk-register
  "构造注册的处理函数"
  [context]
  (fn [{:keys [username password]}]
    (let [conn (:conn context)
          db (d/conn->db conn)
          exist? (core/username-exist? db username)]
      (if-not exist?
        (do (->> (d/gen-account {:username username :password password})
                 (d/update-data conn))
            {:result true :username username})
        {:result false :error "user exist"}))))
