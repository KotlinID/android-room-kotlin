package id.kotlin.sample.room

import id.kotlin.sample.room.data.User

interface MainListener {

    fun onItemClick(user: User)
}