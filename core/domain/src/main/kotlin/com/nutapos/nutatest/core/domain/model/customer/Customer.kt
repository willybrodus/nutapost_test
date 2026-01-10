package com.nutapos.nutatest.core.domain.model.customer

data class Customer(
    val id: Long = 0,
    val name: String,
    val phoneNumber: String?,
    val email: String?,
    val isMember: Boolean = false
)