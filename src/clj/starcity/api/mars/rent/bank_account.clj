(ns starcity.api.mars.rent.bank-account
  (:require [compojure.core :refer [context defroutes GET]]
            [starcity
             [auth :as auth]
             [datomic :refer [conn]]]
            [starcity.models.rent :as rent]
            [starcity.api.mars.rent.bank-account.setup :as setup]
            [starcity.api.common :refer :all]))

(defn bank-account-handler [req]
  (let [requester (auth/requester req)]
    (ok {:bank-account (rent/bank-account requester)})))

(defroutes routes
  (GET "/" [] bank-account-handler)
  (context "/setup" [] setup/routes))
