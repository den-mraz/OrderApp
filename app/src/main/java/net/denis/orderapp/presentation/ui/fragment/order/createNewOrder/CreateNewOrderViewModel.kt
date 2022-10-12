package net.denis.orderapp.presentation.ui.fragment.order.createNewOrder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.denis.orderapp.data.AppDatabase
import net.denis.orderapp.data.local.order.room.entities.order.OrderDbEntity
import net.denis.orderapp.data.repository.OrderRepository.OrderRepository
import net.denis.orderapp.data.repository.OrderRepository.RoomOrderRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class CreateNewOrderViewModel @Inject constructor(
    application: Application,
    private val repository: OrderRepository
): AndroidViewModel(application) {

    fun createNewOrder(orderDbEntity: OrderDbEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.createNewOrder(orderDbEntity)
        }
    }

}