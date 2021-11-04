package org.android.daggerhilt.room.entity

/**
 * Interface for entities to map their structure from api data
 */
interface EntityMapper <Entity, NetworkModel> {
    fun mapEntityToNetworkModel(entity: Entity) : NetworkModel
    fun mapNetworkModelToEntity(networkModel: NetworkModel) : Entity
}