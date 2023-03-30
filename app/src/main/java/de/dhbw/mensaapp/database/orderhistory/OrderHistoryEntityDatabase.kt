package de.dhbw.mensaapp.database.orderhistory

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [OrderHistoryEntity::class], version = 1, exportSchema = false)
abstract class OrderHistoryEntityDatabase : RoomDatabase() {

    abstract val orderHistoryDao: OrderHistoryDao
}
