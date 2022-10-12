package net.denis.orderapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.denis.orderapp.data.local.order.room.dao.OrdersDao
import net.denis.orderapp.data.local.order.room.entities.order.OrderDbEntity
import net.denis.orderapp.data.local.storage.room.dao.StorageDao


@Database(
    entities = [
        OrderDbEntity::class
               ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ordersDao(): OrdersDao

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