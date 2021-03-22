package com.projectKotlinSpring.KotlinSpring.service.impl

import com.projectKotlinSpring.KotlinSpring.entities.User
import com.projectKotlinSpring.KotlinSpring.repository.UserRepository
import com.projectKotlinSpring.KotlinSpring.service.UserService
import com.projectKotlinSpring.KotlinSpring.service.exceptions.DatabaseException
import com.projectKotlinSpring.KotlinSpring.service.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

import java.util.*
import javax.persistence.EntityNotFoundException


@Service
class UserServiceImpl : UserService{

    @Autowired
    lateinit var userRepositoryJpa: UserRepository



    override fun findById(id: Long): User {
        val obj: Optional<User> = this.userRepositoryJpa.findById(id)
        return obj.orElseThrow { ResourceNotFoundException(id.toString()) }
    }


    override fun findAllWithParam(filterByName: String, start: Int, size: Int): List<User> {
        val pages = PageRequest.of(start, size)
        return this.userRepositoryJpa.findAll(pages).filter { it.getName().contains(filterByName, true) }.toList()
    }

    override fun post(obj: User): User{
        return this.userRepositoryJpa.save(obj)
    }


    override fun deleteById(id: Long) {
        try {
            this.userRepositoryJpa.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw ResourceNotFoundException(id.toString())
        } catch (e: DataIntegrityViolationException) {
            throw DatabaseException("Data Integrity Violation")
        }

    }

    override fun put(obj: User, id: Long):User {
        try {
            val entity = this.userRepositoryJpa.getOne(id)
            updateData(obj, entity)
            return this.userRepositoryJpa.save(entity)
        } catch (e: EntityNotFoundException) {
            throw ResourceNotFoundException(id.toString())
        }
    }

    override fun count(): Long {
        return userRepositoryJpa.count()
    }

    override fun findAllSort(paramType: String, paramOrder: String, paramValue: String, start: Int, size: Int): List<User> {
        lateinit var pages : Pageable
       if(paramOrder.toLowerCase() == "asc") {
           pages = PageRequest.of(start, size, Sort.by(paramType).ascending())
       }else if(paramOrder.toLowerCase() == "desc"){
           pages = PageRequest.of(start, size, Sort.by(paramType).descending())
       }

        if(paramType.toLowerCase() == "name") {
            return userRepositoryJpa.findAll(pages).filter { it.getName().contains(paramValue,true) }.toList()
        }

        return userRepositoryJpa.findAll(pages).filter { it.getCpf().contains(paramValue,true) }.toList()
    }
/*
    override fun findAndSortByNameAsc(filterByName: String, start: Int, size: Int): List<User> {
        val pages = PageRequest.of(start, size, Sort.by("name").ascending())
        return userRepositoryJpa.findAll(pages).filter { it.getName().contains(filterByName, true) }.toList()
    }

    override fun findAndSortByNameDesc(filterByName: String, start: Int, size: Int): List<User> {
        val pages = PageRequest.of(start, size, Sort.by("name").descending())
        return this.userRepositoryJpa.findAll(pages).filter { it.getName().contains(filterByName, true) }.toList()
    }

    override fun findAllSortByCpfAsc(filterByCpf: String, start: Int, size: Int): List<User> {
        val pages = PageRequest.of(start, size, Sort.by("cpf" ).ascending())
        return userRepositoryJpa.findAll(pages).filter { it.getCpf().contains(filterByCpf, ignoreCase = true) }.toList()
    }

    override fun findAllSortByCpfDesc(filterByCpf: String, start: Int, size: Int): List<User> {
        val pages = PageRequest.of(start, size, Sort.by("cpf" ).descending())
        return userRepositoryJpa.findAll(pages).filter { it.getCpf().contains(filterByCpf, ignoreCase = true) }.toList()
    }
*/

    fun updateData(obj: User, entity: User) {
            entity.setName(obj.getName())
            entity.setEmail(obj.getEmail())
            entity.setCpf(obj.getCpf())
    }

}