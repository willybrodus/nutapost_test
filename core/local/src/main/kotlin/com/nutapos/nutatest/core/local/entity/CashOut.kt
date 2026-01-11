package com.nutapos.nutatest.core.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "cash_outs",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CashOut(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val note: String,
    val amount: Double,
    val sourceOutcomeType: String,
    val proof: String?,
    val createdAt: Long = System.currentTimeMillis()
)
