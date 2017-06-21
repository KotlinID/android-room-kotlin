package id.kotlin.sample.room.data

import android.os.Parcel
import android.os.Parcelable

data class UserModel(val id: Long,
                     val firstName: String,
                     val lastName: String) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<UserModel> = object : Parcelable.Creator<UserModel> {
            override fun createFromParcel(source: Parcel): UserModel = UserModel(source)
            override fun newArray(size: Int): Array<UserModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(firstName)
        dest.writeString(lastName)
    }
}