package id.smartdev.notaku.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "products")
data class Product(
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "image") var image: String?,
    @ColumnInfo(name = "price") var price: Double?,
    @ColumnInfo(name = "unit") var unit: String?,
    @ColumnInfo(name = "stock") var stock: Double?,
    @ColumnInfo(name = "is_active") var is_active: Boolean?,
    @PrimaryKey(autoGenerate = true) var id: Long? = 0
) : Parcelable