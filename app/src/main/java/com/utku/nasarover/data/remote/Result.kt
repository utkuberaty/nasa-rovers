package com.utku.nasarover.data.remote

sealed class Result<out T> {
    data class Success<out T>(val data: T?) : Result<T>()
    data class Error(val code: Int, val exception: String): Result<Nothing>()
}