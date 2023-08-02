package com.example.androidcleancodemvvm2.domain.common

sealed class Resource<T>(
    open val data: T? = null,
    open val message:String? = null
){
    class Success<T>(data: T):Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T>(data: T? = null): Resource<T>(data)
}