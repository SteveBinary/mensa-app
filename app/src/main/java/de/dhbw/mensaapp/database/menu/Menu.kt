package de.dhbw.mensaapp.database.menu

import androidx.room.*
import kotlin.random.Random
import kotlin.random.nextLong


@Entity(tableName = "menu")
data class MenuEntity(
    val menuId: String,
    val name: String,
    val subTitle: String,
    val priceUnit: String,
    val priceStudent: Float,
    val priceEmployee: Float,
    val priceGuest: Float,
    val foodAdditivesAndAllergens: String,
    val epochDay: Long,

    @PrimaryKey
    val id: Long = Random.nextLong(0 until Long.MAX_VALUE)
)

@Dao
interface MenuDao {

    @Upsert
    suspend fun upsertAllMenuEntity(menuEntities: List<MenuEntity>)

    @Query("SELECT * FROM menu ORDER BY epochDay ASC;")
    fun getMenuEntities(): List<MenuEntity>
}
