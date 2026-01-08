package com.nutapos.nutatest.core.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "cash_inflows",
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CashInflow(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val customerId: Long?,
    val description: String,
    val amount: Double,
    val incomeType: String,
    val proof: String?,
    val createdAt: Long = System.currentTimeMillis()
)
