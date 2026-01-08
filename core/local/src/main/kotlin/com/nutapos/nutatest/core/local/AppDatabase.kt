package com.nutapos.nutatest.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nutapos.nutatest.core.local.dao.CashInflowDao
import com.nutapos.nutatest.core.local.dao.CashOutDao
import com.nutapos.nutatest.core.local.dao.CustomerDao
import com.nutapos.nutatest.core.local.dao.UserDao
import com.nutapos.nutatest.core.local.entity.CashInflow
import com.nutapos.nutatest.core.local.entity.CashOut
import com.nutapos.nutatest.core.local.entity.Customer
import com.nutapos.nutatest.core.local.entity.User

@Database(entities = [Customer::class, CashInflow::class, User::class, CashOut::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun cashInflowDao(): CashInflowDao
    abstract fun userDao(): UserDao
    abstract fun cashOutDao(): CashOutDao
}
