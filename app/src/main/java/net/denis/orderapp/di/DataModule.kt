package net.denis.orderapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.denis.orderapp.data.AppDatabase
import net.denis.orderapp.data.local.order.room.dao.OrdersDao
import net.denis.orderapp.data.repository.OrderRepository.OrderRepository
import net.denis.orderapp.data.repository.OrderRepository.RoomOrderRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "order_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): OrdersDao {
        return appDatabase.ordersDao()
    }

}