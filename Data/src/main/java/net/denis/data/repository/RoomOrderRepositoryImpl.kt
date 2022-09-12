package net.denis.data.repository

import androidx.lifecycle.LiveData
import net.denis.data.model.room.OrdersDao
import net.denis.data.model.room.entities.OrderDbEntity

class RoomOrderRepositoryImpl(
    private val ordersDao: OrdersDao
) {
    val readArchive: LiveData<List<OrderDbEntity>> = ordersDao.getArchive()
    val readDashboard: LiveData<List<OrderDbEntity>> = ordersDao.getDashboard()

    suspend fun createNewOrder(orderDbEntity: OrderDbEntity) {
        ordersDao.createNewOrder(orderDbEntity)
    }

    suspend fun updateOrder(orderDbEntity: OrderDbEntity) {
        ordersDao.updateOrder(orderDbEntity)
    }

    suspend fun deleteOrder(orderDbEntity: OrderDbEntity) {
       ordersDao.deleteOrder(orderDbEntity)
    }

}

