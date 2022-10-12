package net.denis.orderapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import net.denis.orderapp.data.repository.OrderRepository.OrderRepository
import net.denis.orderapp.data.repository.OrderRepository.RoomOrderRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideOrderRepository(repository: RoomOrderRepositoryImpl): OrderRepository

}

//    fun provideOrderRepository(ordersDao: OrdersDao): OrderRepository
// {
//        return RoomOrderRepositoryImpl(ordersDao)
//    }