package net.denis.data.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import net.denis.data.model.room.entities.storage.category.CategoryToolEntity

@Dao
interface StorageDao {

    @Insert
    suspend fun AddCategoryTool(categoryToolEntity: CategoryToolEntity)

    @Delete
    suspend fun DeleteAtCategoryTool(categoryToolEntity: CategoryToolEntity)

    @Update
    suspend fun UpdateCategoryTool(categoryToolEntity: CategoryToolEntity)

    @Query("SELECT * FROM 'category_tool' ORDER BY name_category_tool")
    fun getCategory(): LiveData<List<CategoryToolEntity>>



}