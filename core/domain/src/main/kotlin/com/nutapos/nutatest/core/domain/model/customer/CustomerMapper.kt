package com.nutapos.nutatest.core.domain.model.customer

// Maps Data-layer Customer to Domain-layer Customer
import com.nutapos.nutatest.core.local.entity.Customer as Entity

fun Entity.toDomainModel(): Customer {
    return Customer(
        id = this.id,
        name = this.name,
        phoneNumber = this.phoneNumber,
        email = this.email,
        isMember = this.isMember
    )
}

// Maps Domain-layer Customer to Data-layer Customer
fun Customer.toEntity(): Entity {
    return Entity(
        id = this.id,
        name = this.name,
        phoneNumber = this.phoneNumber,
        email = this.email,
        isMember = this.isMember
    )
}
