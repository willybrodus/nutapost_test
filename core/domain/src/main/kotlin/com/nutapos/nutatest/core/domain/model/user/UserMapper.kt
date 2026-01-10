package com.nutapos.nutatest.core.domain.model.user

// Maps Data-layer User to Domain-layer User
fun com.nutapos.nutatest.core.local.entity.User.toDomainModel(): com.nutapos.nutatest.core.domain.model.user.User {
    return com.nutapos.nutatest.core.domain.model.user.User(
        id = this.id,
        name = this.name,
        isLogin = this.isLogin
    )
}

// Maps Domain-layer User to Data-layer User
fun com.nutapos.nutatest.core.domain.model.user.User.toEntity(): com.nutapos.nutatest.core.local.entity.User {
    return com.nutapos.nutatest.core.local.entity.User(
        id = this.id,
        name = this.name,
        isLogin = this.isLogin
    )
}
