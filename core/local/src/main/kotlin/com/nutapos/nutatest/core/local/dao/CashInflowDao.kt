package com.nutapos.nutatest.core.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nutapos.nutatest.core.local.entity.CashInflow

@Dao
interface CashInflowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCashInflow(cashInflow: CashInflow)

    @Query("SELECT * FROM cash_inflows ORDER BY createdAt DESC")
    suspend fun getAllCashInflows(): List<CashInflow>
}
