package com.projectKotlinSpring.KotlinSpring.controller

import com.fasterxml.jackson.annotation.JsonInclude
import com.projectKotlinSpring.KotlinSpring.entities.User
import com.projectKotlinSpring.KotlinSpring.service.UserService
import com.projectKotlinSpring.KotlinSpring.service.impl.UserServiceImpl
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.net.ssl.HttpsURLConnection

@RestController
@RequestMapping ("/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping ("/{id}")
    fun findUserById(@PathVariable id: Long): ResponseEntity<User?> {
        val obj = userService.findById(id)
        val status = if(obj != null) HttpStatus.OK else HttpStatus.NOT_FOUND
        return ResponseEntity(obj,status)
    }


    @GetMapping
    fun findAllWithParam(@RequestParam(required = false, defaultValue = "") filterByName: String,
                @RequestParam(required = false, defaultValue = "0") start: Int,
                @RequestParam(required = false, defaultValue = "5") size: Int) : ResponseEntity<List<User>> {
        val listOfUsers: List<User> = (userService.findAllWithParam(filterByName, start, size))
        val status = if(listOfUsers != null) HttpStatus.OK else HttpStatus.NOT_FOUND
        return ResponseEntity(listOfUsers, status)
    }


   @GetMapping("/sort")
   fun finaAllSort(@RequestParam(required = false, defaultValue = "") paramType: String,
                   @RequestParam(required = false, defaultValue = "") paramOrder: String,
                   @RequestParam(required = false, defaultValue = "") paramValue: String,
                   @RequestParam(required = false, defaultValue = "0") start: Int,
                   @RequestParam(required = false, defaultValue = "5") size: Int)
   : ResponseEntity<List<User>>{
       val listOfUsers = userService.findAllSort(paramType, paramOrder, paramValue, start, size)
       val status = if(listOfUsers != null) HttpStatus.OK else HttpStatus.NOT_FOUND
       return ResponseEntity(listOfUsers,status)
   }

    @GetMapping ("/count")
    fun count(): ResponseEntity<Map<String,Long>>{
        return ResponseEntity.ok().body(mapOf("count" to this.userService.count()))
    }


    @PostMapping
    fun postUser(@Validated @RequestBody  obj: User ):ResponseEntity<User>{
        val entity = userService.post(obj)
        val uri: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri()
        val status = if(entity != null) HttpStatus.CREATED  else HttpStatus.NOT_FOUND
        return ResponseEntity(entity,status)
    }

    @DeleteMapping ("/{id}")
    fun deleteUser(@PathVariable id: Long):ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if(userService.findById(id) != null){
            userService.deleteById(id)
            status = HttpStatus.NO_CONTENT
        }
        return ResponseEntity(Unit,status)
    }

    @PutMapping ("/{id}")
    fun putUser(@PathVariable id: Long, @Validated @RequestBody obj: User):ResponseEntity<User>{
        var status = HttpStatus.NOT_FOUND
        if(this.userService.findById(id) != null){
            userService.put(obj, id)
            status = HttpStatus.OK
        }
        return ResponseEntity(obj,status)
    }



}