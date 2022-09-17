package net.denis.data.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import net.denis.data.model.room.entities.storage.category.CategoryEntity

interface StorageDao {

    @Insert
    suspend fun AddCategory(categoryEntity: CategoryEntity)

    @Delete
    suspend fun DeleteAtCategory(categoryEntity: CategoryEntity)

    @Update
    suspend fun UpdateCategory(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM 'category' ORDER BY name_category")
    fun getCategory(): LiveData<List<CategoryEntity>>

}