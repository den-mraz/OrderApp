package net.denis.orderapp.data.repository.StorageRepository

import net.denis.orderapp.data.local.storage.room.dao.StorageDao
import javax.inject.Inject


class RoomStorageRepositoryImpl @Inject constructor (
    private val storageDao: StorageDao
) {
/*

    val readCategory: LiveData<List<CategoryToolEntity>> = storageDao.getCategory()

    suspend fun createNewCategoryTool(categoryToolEntity: CategoryToolEntity) {
        storageDao.AddCategoryTool(categoryToolEntity)
    }

    suspend fun updateCategoryTool(categoryToolEntity: CategoryToolEntity) {
        storageDao.UpdateCategoryTool(categoryToolEntity)
    }

    suspend fun deleteCategoryTool(categoryToolEntity: CategoryToolEntity) {
        storageDao.DeleteAtCategoryTool(categoryToolEntity)
    }
*/

}