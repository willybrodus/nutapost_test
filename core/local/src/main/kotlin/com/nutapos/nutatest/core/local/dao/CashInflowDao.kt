package com.nutapos.nutatest.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nutapos.nutatest.core.local.entity.CashInflow
import kotlinx.coroutines.flow.Flow

@Dao
interface CashInflowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCashInflow(cashInflow: CashInflow)

    @Update
    suspend fun updateCashInflow(cashInflow: CashInflow)

    @Delete
    suspend fun deleteCashInflow(cashInflow: CashInflow)

    @Query("SELECT * FROM cash_inflows WHERE id = :id")
    fun getCashInflowById(id: Long): Flow<CashInflow?>

    @Query("SELECT * FROM cash_inflows ORDER BY createdAt DESC")
    fun getAllCashInflows(): Flow<List<CashInflow>>
}
