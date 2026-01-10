package com.nutapos.nutatest.core.data.di

import com.nutapos.nutatest.core.data.repository.CashInflowRepository
import com.nutapos.nutatest.core.data.repository.CashInflowRepositoryImpl
import com.nutapos.nutatest.core.data.repository.CashOutRepository
import com.nutapos.nutatest.core.data.repository.CashOutRepositoryImpl
import com.nutapos.nutatest.core.data.repository.CustomerRepository
import com.nutapos.nutatest.core.data.repository.CustomerRepositoryImpl
import com.nutapos.nutatest.core.data.repository.UserRepository
import com.nutapos.nutatest.core.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindCustomerRepository(customerRepositoryImpl: CustomerRepositoryImpl): CustomerRepository

    @Binds
    @Singleton
    abstract fun bindCashInflowRepository(cashInflowRepositoryImpl: CashInflowRepositoryImpl): CashInflowRepository

    @Binds
    @Singleton
    abstract fun bindCashOutRepository(cashOutRepositoryImpl: CashOutRepositoryImpl): CashOutRepository
}
