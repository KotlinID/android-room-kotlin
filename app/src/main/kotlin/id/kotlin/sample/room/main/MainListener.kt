package id.kotlin.sample.room.main

import id.kotlin.sample.room.data.User

interface MainListener {

    fun onItemClick(user: User)

    fun onItemLongClick(user: User)
}