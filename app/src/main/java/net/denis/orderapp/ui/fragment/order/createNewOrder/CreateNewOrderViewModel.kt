package net.denis.orderapp.ui.fragment.order.createNewOrder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.denis.data.model.room.AppDatabase
import net.denis.data.model.room.entities.OrderDbEntity
import net.denis.data.repository.RoomOrderRepositoryImpl

class CreateNewOrderViewModel(application: Application): AndroidViewModel(application) {

    private val repositoryImpl: RoomOrderRepositoryImpl

    init {
        val ordersDao = AppDatabase.getDatabase(application).ordersDao()
        repositoryImpl = RoomOrderRepositoryImpl(ordersDao)
    }

    fun createNewOrder(orderDbEntity: OrderDbEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.createNewOrder(orderDbEntity)
        }
    }

}