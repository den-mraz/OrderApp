package net.denis.data.model.room.entities.storage.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "category"
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "name_category")
    val nameCategory: String

) : Serializable
