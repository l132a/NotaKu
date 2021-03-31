package id.smartdev.notaku.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "transaction_details")
class TransactionDetail(
    @ColumnInfo(name = "transaction_date") var transaction_date: Date?,
    @ColumnInfo(name = "transaction_id") var transaction_id: Long?,
    @ColumnInfo(name = "product_id") var product_id: Long?,
    @ColumnInfo(name = "product_name") var product_name: String?,
    @ColumnInfo(name = "qty") var qty: Double?,
    @ColumnInfo(name = "discount") var discount: Int?,
    @ColumnInfo(name = "price") var pay: Int?,
    @ColumnInfo(name = "amount") var amount: Int?,
    @PrimaryKey(autoGenerate = true) var id: Long? = 0
) : Parcelable