package net.denis.orderapp.data.repository.OrderRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.denis.orderapp.data.local.order.room.dao.OrdersDao
import net.denis.orderapp.data.local.order.room.entities.order.OrderDbEntity
import javax.inject.Inject

class RoomOrderRepositoryImpl @Inject constructor(
    private val ordersDao: OrdersDao
): OrderRepository {

    override fun getDashboard(): LiveData<List<OrderDbEntity>> {
        return ordersDao.getDashboard()
    }

    override fun getArchive(): LiveData<List<OrderDbEntity>> = ordersDao.getArchive()

    override suspend fun createNewOrder(order: OrderDbEntity) {
        return ordersDao.createNewOrder(order)
    }

    override suspend fun updateOrder(order: OrderDbEntity) = ordersDao.updateOrder(order)

    override suspend fun deleteOrder(order: OrderDbEntity) = ordersDao.deleteOrder(order)

}

/*
val readArchive: LiveData<List<OrderDbEntity>> = ordersDao.getArchive()
val readDashboard: MutableLiveData<List<OrderDbEntity>> = ordersDao.getDashboard()

suspend fun createNewOrder(orderDbEntity: OrderDbEntity) {
    ordersDao.createNewOrder(orderDbEntity)
}

suspend fun updateOrder(orderDbEntity: OrderDbEntity) {
    ordersDao.updateOrder(orderDbEntity)
}

suspend fun deleteOrder(orderDbEntity: OrderDbEntity) {
   ordersDao.deleteOrder(orderDbEntity)
}
/*    fun getDashboard(): MutableLiveData<List<OrderDbEntity>> = ordersDao.getDashboard()

    fun getArchive(): MutableLiveData<List<OrderDbEntity>> = ordersDao.getArchive()*/
}
*/
