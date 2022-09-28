package net.denis.data.model.room.entities.storage.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "category_tool"
)
data class CategoryToolEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "name_category_tool")
    val nameCategoryTool: String

) : Serializable
