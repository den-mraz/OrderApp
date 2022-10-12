package net.denis.orderapp.presentation.ui.fragment.dashboard

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.denis.orderapp.data.local.order.room.entities.order.OrderDbEntity
import net.denis.orderapp.data.repository.OrderRepository.OrderRepository
import net.denis.orderapp.data.repository.OrderRepository.RoomOrderRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    private val repository: OrderRepository
): AndroidViewModel(application) {

    private var _dashboardData: LiveData<List<OrderDbEntity>> = repository.getDashboard()

    val dashboardData: LiveData<List<OrderDbEntity>>
        get() {
            return _dashboardData
        }

    fun updateOrder(orderDbEntity: OrderDbEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateOrder(orderDbEntity)
        }
    }

    fun deleteOrder(orderDbEntity: OrderDbEntity) = viewModelScope.launch {
        repository.deleteOrder(orderDbEntity)
    }

}