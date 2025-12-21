package io.jadu.data.model

import io.jadu.data.db.local.Necessity
import io.jadu.data.db.local.TransactionType
import kotlinx.serialization.Serializable

@Serializable
data class Transaction (
    val id: Int = 0,
    val userId: Int,
    val title: String,
    val amount: Double,
    val type: TransactionType,
    val necessity: Necessity,
    val category: String,
    val date: String
)