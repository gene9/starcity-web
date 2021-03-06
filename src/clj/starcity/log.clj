(ns starcity.log
  (:require [cheshire.core :as json]
            [clojure.spec :as s]
            [mount.core :as mount :refer [defstate]]
            [starcity
             [config :refer [config]]
             [environment :refer [environment]]]
            [taoensso.timbre :as timbre :refer [merge-config!]]
            [taoensso.timbre.appenders.3rd-party.rolling :as rolling]
            [taoensso.timbre.appenders.core :as appenders]))

;; =============================================================================
;; Configuration
;; =============================================================================

(defn- appenders-for-environment
  [{:keys [logfile]}]
  (let [default {:spit (appenders/spit-appender {:fname logfile})}]
    (get {:production {:rolling (rolling/rolling-appender {:path logfile})}
          :staging    {:rolling (rolling/rolling-appender {:path logfile})}}
         environment
         default)))

(defn- throwable? [x]
  (instance? java.lang.Throwable x))

(s/def ::event-vargs
  (s/cat :event keyword?
         :params (s/? map?)))

(defn- wrap-event-format
  "Middleware that transforms the user's log input into a JSON
  string with an `event` key. This is used to make search effective in LogDNA.

  Only applies when timbre is called with input of the form:

  (timbre/info ::event {:map :of-data})"
  [{:keys [vargs] :as data}]
  (if (s/valid? ::event-vargs vargs)
    (let [{:keys [event params]} (s/conform ::event-vargs vargs)]
      (assoc data :vargs [(-> {:event event}
                              (merge (when-let [err (:?err data)] {:error-data (ex-data err)})
                                     params)
                              json/generate-string)]))
    data))

(defn- setup-logger
  [{:keys [level logfile] :as conf}]
  (timbre/debug ::start {:level level :file logfile})
  (merge-config!
   {:level      level
    :middleware [wrap-event-format]
    :appenders  (appenders-for-environment conf)}))

(defstate logger :start (setup-logger (:log config)))

(comment
  (timbre/info (ex-info "Yikes!" {:data 42}) ::an-event {:message "A message."})

  )
