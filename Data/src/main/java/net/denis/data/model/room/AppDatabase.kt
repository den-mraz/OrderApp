package net.denis.data.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.denis.data.model.room.entities.order.OrderDbEntity
import net.denis.data.model.room.dao.OrdersDao
import net.denis.data.model.room.dao.StorageDao
import net.denis.data.model.room.entities.storage.category.CategoryToolEntity

@Database(
    entities = [
        OrderDbEntity::class,
        CategoryToolEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ordersDao(): OrdersDao
    abstract fun storageDao(): StorageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "order_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}