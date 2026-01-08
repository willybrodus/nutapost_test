package com.nutapos.nutatest.core.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nutapos.nutatest.core.local.entity.CashOut

@Dao
interface CashOutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCashOut(cashOut: CashOut)

    @Query("SELECT * FROM cash_outs ORDER BY createdAt DESC")
    suspend fun getAllCashOuts(): List<CashOut>
}
