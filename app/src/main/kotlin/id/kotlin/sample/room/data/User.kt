package id.kotlin.sample.room.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class User constructor(@PrimaryKey val id: Long,
                            @ColumnInfo(name = "first_name") val firstName: String,
                            @ColumnInfo(name = "last_name") val lastName: String)