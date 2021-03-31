package id.smartdev.notaku.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "customers")
data class Customer(
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "phone") var phone: String?,
    @ColumnInfo(name = "address") var address: String?,
    @PrimaryKey(autoGenerate = true) var id: Long? = 0
) : Parcelable