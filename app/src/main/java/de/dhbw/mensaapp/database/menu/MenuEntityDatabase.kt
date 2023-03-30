package de.dhbw.mensaapp.database.menu

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MenuEntity::class], version = 1, exportSchema = false)
abstract class MenuEntityDatabase : RoomDatabase() {

    abstract val menuDao: MenuDao
}
