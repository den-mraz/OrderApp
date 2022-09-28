package net.denis.orderapp.ui.fragment.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.denis.data.model.room.AppDatabase
import net.denis.data.model.room.entities.storage.category.CategoryToolEntity
import net.denis.data.repository.StorageRepository.RoomStorageRepositoryImpl

class StorageViewModel(application: Application) : AndroidViewModel(application) {

    val readVMStorage: LiveData<List<CategoryToolEntity>>
    private val repositoryImpl: RoomStorageRepositoryImpl

    init {
        val storageDao = AppDatabase.getDatabase(application).storageDao()
        repositoryImpl = RoomStorageRepositoryImpl(storageDao)
        readVMStorage = repositoryImpl.readCategory
    }

    fun createNewCategory(categoryToolEntity: CategoryToolEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.createNewCategory(categoryToolEntity)
        }
    }

    fun updateCategory(categoryToolEntity: CategoryToolEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.updateCategory(categoryToolEntity)
        }
    }

    fun deleteCategory(categoryToolEntity: CategoryToolEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.deleteCategory(categoryToolEntity)
        }
    }
}
