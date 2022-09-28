package net.denis.data.repository.StorageRepository

import androidx.lifecycle.LiveData
import net.denis.data.model.room.dao.StorageDao
import net.denis.data.model.room.entities.storage.category.CategoryToolEntity


class RoomStorageRepositoryImpl(
    private val storageDao: StorageDao
) {

    val readCategory: LiveData<List<CategoryToolEntity>> = storageDao.getCategory()

    suspend fun createNewCategory(categoryToolEntity: CategoryToolEntity){
        storageDao.AddCategoryTool(categoryToolEntity)
    }

    suspend fun updateCategory(categoryToolEntity: CategoryToolEntity){
        storageDao.UpdateCategoryTool(categoryToolEntity)
    }

    suspend fun deleteCategory(categoryToolEntity: CategoryToolEntity){
        storageDao.DeleteAtCategoryTool(categoryToolEntity)
    }

}