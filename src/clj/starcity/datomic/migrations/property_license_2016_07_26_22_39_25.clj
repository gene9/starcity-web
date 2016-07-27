(ns starcity.datomic.migrations.property-license-2016-07-26-22-39-25)

(def migration
  [{:db/id                 #db/id[:db.part/db]
    :db/ident              :property-license/license
    :db/valueType          :db.type/ref
    :db/cardinality        :db.cardinality/one
    :db/doc                "Reference to a license for a specific property."
    :db.install/_attribute :db.part/db}

   {:db/id                 #db/id[:db.part/db]
    :db/ident              :property-license/base-price
    :db/valueType          :db.type/float
    :db/cardinality        :db.cardinality/one
    :db/doc                "The base price for this license at this property."
    :db.install/_attribute :db.part/db}])

(def norms
  {:starcity/property-license-2016-07-26-22-39-25 {:txes [migration]}})
