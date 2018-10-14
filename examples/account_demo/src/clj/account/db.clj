(ns account.db
  "datomic数据库"
  (:require [datomic.api :as d]))

(def account-schema
  "数据库账户数据定义"
  [#:db{:ident       :account/username
        :doc         "用户名"
        :cardinality :db.cardinality/one
        :valueType   :db.type/string
        :unique      :db.unique/identity}
   #:db{:ident       :account/password
        :doc         "密码"
        :cardinality :db.cardinality/one
        :valueType   :db.type/string}])

(defn gen-account
  "组装账户数据"
  [{:keys [username password]}]
  [{:account/username username
    :account/password password}])

(defn find-account
  "查询账户数据"
  [db username]
  (d/q '[:find (pull ?e [*]) .
         :in $ ?username
         :where
         [?e :account/username ?username]]
       db username))

(defn update-data
  "更新数据库数据"
  [conn data]
  @(d/transact conn data))

(defn conn->db
  "从连接获取数据"
  [conn]
  (d/db conn))

(defn mk-conn
  "初始化数据库连接"
  [uri db-schema]
  (d/create-database uri)
  (let [conn (d/connect uri)]
    @(d/transact conn db-schema)
    conn))

(comment
  (def conn (mk-conn "datomic:mem:acc-repl" account-schema))
  (d/transact conn (gen-account {:username "foo" :password "123456"}))
  (find-account (d/db conn) "foo")
  (d/delete-database "datomic:mem:acc-repl"))
