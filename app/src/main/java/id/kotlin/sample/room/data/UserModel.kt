package id.kotlin.sample.room.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class UserModel(val id: Long,
                     val firstName: String,
                     val lastName: String) : Parcelable {
    companion object CREATOR : Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
    }

    override fun describeContents(): Int {
        return 0
    }
}