(ns starcity.datomic.migrations
  (:require [starcity.datomic.migrations
             [initial :refer [initial-migration]]
             [update-properties-descriptions-8-2-16 :refer [update-properties-descriptions]]
             [income-files-8-3-16 :refer [add-income-files-schema]]
             [seed-test-applications-8-4-16 :refer [seed-test-applications]]
             [add-account-role-pending-8-18-16 :refer [add-account-role-pending]]
             [add-security-deposit-schema-8-18-16 :refer [add-security-deposit-schema]]
             [add-stripe-customer-schema-8-30-16 :refer [add-stripe-customer-schema]]
             [onboarding-updates-9-8-16 :refer [add-stripe-credentials-to-property-schema
                                                seed-stripe-test-credentials]]
             [add-approval-schema-9-8-16 :refer [add-approval-schema
                                                 seed-test-approval]]
             [add-community-safety-consent-9-28-16 :refer [add-community-safety-consent]]]
            [starcity.datomic.migrations.utils :refer [only-when]]
            [starcity.environment]
            [mount.core :refer [defstate]]))

(defn migration-norms [conn]
  (merge
   (initial-migration conn)
   update-properties-descriptions
   add-income-files-schema
   (only-when #{:development} seed-test-applications)
   add-account-role-pending
   add-security-deposit-schema
   add-stripe-customer-schema
   add-stripe-credentials-to-property-schema
   (only-when #{:development :staging} seed-stripe-test-credentials)
   add-approval-schema
   (only-when #{:development :staging} seed-test-approval)
   add-community-safety-consent))
