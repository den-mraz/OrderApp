package net.denis.orderapp.ui.fragment.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.denis.data.model.room.AppDatabase
import net.denis.data.model.room.entities.OrderDbEntity
import net.denis.data.repository.RoomOrderRepositoryImpl

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    val readVMDashboard: LiveData<List<OrderDbEntity>>
    private val repositoryImpl: RoomOrderRepositoryImpl

    init {
        val ordersDao = AppDatabase.getDatabase(application).ordersDao()
        repositoryImpl = RoomOrderRepositoryImpl(ordersDao)
        readVMDashboard = repositoryImpl.readDashboard
    }

    fun updateOrder(orderDbEntity: OrderDbEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.updateOrder(orderDbEntity)
        }
    }

    fun deleteOrder(orderDbEntity: OrderDbEntity) = viewModelScope.launch {
        repositoryImpl.deleteOrder(orderDbEntity)
    }

}