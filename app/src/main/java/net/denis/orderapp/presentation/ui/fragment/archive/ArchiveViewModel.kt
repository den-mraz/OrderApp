package net.denis.orderapp.presentation.ui.fragment.archive

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.denis.orderapp.data.AppDatabase
import net.denis.orderapp.data.local.order.room.entities.order.OrderDbEntity
import net.denis.orderapp.data.repository.OrderRepository.OrderRepository
import net.denis.orderapp.data.repository.OrderRepository.RoomOrderRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor (
    application: Application,
    private val repository: OrderRepository
    ) : AndroidViewModel(application) {

    private val _archiveData : LiveData<List<OrderDbEntity>> = repository.getArchive()

    val archiveData: LiveData<List<OrderDbEntity>>
        get() {
            return _archiveData
        }

    fun moveInDashboard(orderDbEntity: OrderDbEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateOrder(orderDbEntity)
        }
    }

}

/*
    application: Application
    ) : AndroidViewModel(application) {
    private var repository: OrderRepository

    private val _archiveData : LiveData<List<OrderDbEntity>>

    init {
        val ordersDao = AppDatabase.getDatabase(application).ordersDao()
        repository = RoomOrderRepositoryImpl(ordersDao)
        _archiveData = repository.getArchive()
    }

    val archiveData: LiveData<List<OrderDbEntity>>
        get() {
            return _archiveData
        }

    fun moveInDashboard(orderDbEntity: OrderDbEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateOrder(orderDbEntity)
        }
    }

 */