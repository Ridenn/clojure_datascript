(ns datascript-estudo.core
  (require '[datascript.core :as d]))

(let [schema {:aka {:db/cardinality :db.cardinality/many}}
        conn   (d/create-conn schema)]
    (d/transact! conn [ { :db/id 1
                         :name  "Lucas"
                         :age   23
                         :aka   ["Pedrosa", "Lucas Pedrosa Ribeiro"]}
                       { :db/id 2
                        :name  "Jorge"
                        :age   40
                        :aka   ["Jorge", "Jorgin"]}])
    (d/q '[ :find  ?nome ?idade
           :where [?valor :aka "Jorge"]
           [?valor :name ?nome]
           [?valor :age  ?idade]]
         @conn))