package net.denis.orderapp.ui.fragment.archive

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.denis.data.model.room.AppDatabase
import net.denis.data.model.room.entities.OrderDbEntity
import net.denis.data.repository.RoomOrderRepositoryImpl

class ArchiveViewModel(application: Application) : AndroidViewModel(application) {

    val readVMArchive: LiveData<List<OrderDbEntity>>
    private val repositoryImpl: RoomOrderRepositoryImpl

    init {
        val ordersDao = AppDatabase.getDatabase(application).ordersDao()
        repositoryImpl = RoomOrderRepositoryImpl(ordersDao)
        readVMArchive = repositoryImpl.readArchive
    }

    fun moveInDashboard(orderDbEntity: OrderDbEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.updateOrder(orderDbEntity)
        }
    }

}