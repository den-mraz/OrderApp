package net.denis.orderapp.common

sealed class Resourse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resourse<T>(data)
    class Error<T>(data: T? = null, message: String?) : Resourse<T>(data, message)
    class Loading<T>(data: T? = null) : Resourse<T>(data)
}
