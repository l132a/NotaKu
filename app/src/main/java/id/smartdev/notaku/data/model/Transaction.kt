package id.smartdev.notaku.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "transactions")
data class Transaction(
    @ColumnInfo(name = "customer_id") var customer_id: Long?,
    @ColumnInfo(name = "transaction_date") var transaction_date: Date?,
    @ColumnInfo(name = "qty") var qty: Double?,
    @ColumnInfo(name = "discount") var discount: Int?,
    @ColumnInfo(name = "amount") var amount: Int?,
    @ColumnInfo(name = "pay") var pay: Int?,
    @ColumnInfo(name = "change") var change: Int?,
    @PrimaryKey(autoGenerate = true) var id: Long? = 0
) : Parcelable