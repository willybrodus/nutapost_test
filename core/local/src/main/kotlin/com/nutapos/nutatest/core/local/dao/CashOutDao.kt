package com.nutapos.nutatest.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nutapos.nutatest.core.local.entity.CashOut
import kotlinx.coroutines.flow.Flow

@Dao
interface CashOutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCashOut(cashOut: CashOut)

    @Update
    suspend fun updateCashOut(cashOut: CashOut)

    @Delete
    suspend fun deleteCashOut(cashOut: CashOut)

    @Query("SELECT * FROM cash_outs WHERE id = :id")
    fun getCashOutById(id: Long): Flow<CashOut?>

    @Query("SELECT * FROM cash_outs ORDER BY createdAt DESC")
    fun getAllCashOuts(): Flow<List<CashOut>>
}
