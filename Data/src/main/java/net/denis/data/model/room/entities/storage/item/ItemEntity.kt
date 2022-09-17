package net.denis.data.model.room.entities.storage.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "item"
)
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "item_name")
    val itemName: String

) : Serializable
