package com.projectKotlinSpring.KotlinSpring.service

import com.projectKotlinSpring.KotlinSpring.entities.User
import org.springframework.stereotype.Service
import java.util.*

@Service
interface UserService {

    fun findById(id: Long): User?
    fun findAllWithParam(filterByName: String, start: Int, size: Int): List<User>
    fun post(obj: User): User
    fun deleteById(id: Long)
    fun put(obj: User, id: Long): User
    fun count() : Long
    fun findAllSort(paramType: String, paramOrder: String, paramValue: String, start: Int, size: Int): List<User>


}