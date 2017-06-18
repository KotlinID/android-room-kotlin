package id.kotlin.sample.room.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun findAll(): List<User>
}