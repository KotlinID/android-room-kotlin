package id.kotlin.sample.room.extensions

internal inline fun <reified T : Any> objectOf() = T::class.java