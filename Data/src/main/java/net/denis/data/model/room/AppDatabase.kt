package net.denis.data.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.denis.data.model.room.entities.OrderDbEntity

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
            if (tempInstance!= null) {
                return tempInstance
            }
            synchronized(this){
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

    /*
    companion object {
        private var database: AppDatabase? = null

        @Synchronized
        fun newInstance(context: Context): AppDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, AppDatabase::class.java, "db").build()
                database as AppDatabase
            } else {
                database as AppDatabase
            }
        }
    }

     */
}