package com.nutapos.nutatest.core.domain.model.user

// Maps Data-layer Customer to Domain-layer Customer
import com.nutapos.nutatest.core.local.entity.User as Entity

fun Entity.toDomainModel(): User {
    return User(
        id = this.id,
        name = this.name,
    )
}

// Maps Domain-layer Customer to Data-layer Customer
fun User.toEntity(): Entity {
    return Entity(
        id = this.id,
        name = this.name,
    )
}
