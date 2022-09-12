package net.denis.data.model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import net.denis.data.model.room.entities.OrderDbEntity

@Dao
interface OrdersDao {
    @Insert
    suspend fun createNewOrder(orderDbEntity: OrderDbEntity)

    @Update
    suspend fun updateOrder(orderDbEntity: OrderDbEntity)

    @Delete
    suspend fun deleteOrder(orderDbEntity: OrderDbEntity)

    @Query("SELECT * FROM 'order' WHERE status = 0 ORDER BY id DESC")
    fun getDashboard(): LiveData<List<OrderDbEntity>>

    @Query("SELECT * FROM 'order' WHERE status = 1 ORDER BY date_end DESC")
    fun getArchive(): LiveData<List<OrderDbEntity>>

}