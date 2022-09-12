package net.denis.data.model.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(
    tableName = "order"
)
data class OrderDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "name_product")
    val nameProduct: String,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "customer_name")
    val customerName: String,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "phone_number")
    val phoneNumber: String,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "date_start")
    val dateStart: String,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "corporation")
    val corporation: String,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "note")
    val note: String,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "prepayment")
    val prepayment: Double,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "amount")
    val amount: Double,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "date_end")
    val dateEnd: String,

    @ColumnInfo(collate = ColumnInfo.NOCASE, name = "status", defaultValue = false.toString())
    val status: Boolean

): Serializable