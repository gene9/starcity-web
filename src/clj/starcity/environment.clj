(ns starcity.environment
  (:require [mount.core :as mount :refer [defstate]]))

;; NOTE: The default implementation is to start the system in the :development
;; environment. During production, the implementation should be replaced with
;; the :production environment.
(defstate environment :start :development)

(defn is-development? []
  (= environment :development))

(defn is-production? []
  (= environment :production))

(defn is-staging? []
  (= environment :staging))
