package de.dhbw.mensaapp.database.orderhistory

import androidx.room.*
import kotlin.random.Random
import kotlin.random.nextLong


@Entity(tableName = "order_history")
data class OrderHistoryEntity(
    val name: String,
    val subtitle: String,
    val epochDay: Long,
    val timeSlot: String,
    val count: Int,

    @PrimaryKey
    val id: Long = Random.nextLong(0 until Long.MAX_VALUE)
)

@Dao
interface OrderHistoryDao {

    @Upsert
    suspend fun upsertAllOrderEntities(orderEntities: List<OrderHistoryEntity>)

    @Query("SELECT * FROM order_history ORDER BY epochDay ASC;")
    fun getOrderEntities(): List<OrderHistoryEntity>
}
