package net.denis.orderapp.data.repository.OrderRepository

import androidx.lifecycle.LiveData
import net.denis.orderapp.data.local.order.room.entities.order.OrderDbEntity

interface OrderRepository {

    fun getDashboard(): LiveData<List<OrderDbEntity>>

    fun getArchive(): LiveData<List<OrderDbEntity>>

    suspend fun createNewOrder(order: OrderDbEntity)

    suspend fun updateOrder(order: OrderDbEntity)

    suspend fun deleteOrder(order: OrderDbEntity)

}