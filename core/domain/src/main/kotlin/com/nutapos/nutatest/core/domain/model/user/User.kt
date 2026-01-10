package com.nutapos.nutatest.core.domain.model.user

data class User(
  val id: Long = 0,
  val name: String,
  val isLogin: Boolean = false
)