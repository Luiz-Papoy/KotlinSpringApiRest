package com.projectKotlinSpring.KotlinSpring.controller.errors

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.Instant

class StandartError(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone =  "GMT")
    private var timestamp: Instant,

    private var status: Int,
    private var error: String?,
    private var message: String?,
    private var path: String
) : Serializable {

    fun getTimeStamp() = this.timestamp
    fun getStatus() = this.status
    fun getError() = this.error
    fun getMessage() = this.message
    fun getPath() = this.path


    fun setTimeStamp(timestamp: Instant){
        this.timestamp = timestamp
    }

    fun setStatus(status: Int){
        this.status = status
    }

    fun setError(error: String){
        this.error = error
    }

    fun setMessage(message: String){
        this.message = message
    }

    fun setPath(path: String){
        this.path = path
    }



}