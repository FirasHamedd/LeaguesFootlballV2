package com.example.leaguesfootballv2.core

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure<out T>(val message: String? = null, val exception: Exception? = null) : Result<T>()
}