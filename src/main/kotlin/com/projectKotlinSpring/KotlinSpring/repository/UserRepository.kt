package com.projectKotlinSpring.KotlinSpring.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import com.projectKotlinSpring.KotlinSpring.entities.User as User

@Repository
interface UserRepository : JpaRepository<User,Long> {

}