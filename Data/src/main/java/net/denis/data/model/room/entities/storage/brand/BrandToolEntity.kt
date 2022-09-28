package net.denis.data.model.room.entities.storage.brand

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "brand_tool"
)
data class BrandToolEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "name_brand_tool")
    val nameBrandTool: String

) : Serializable
