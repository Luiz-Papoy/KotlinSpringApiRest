package com.projectKotlinSpring.KotlinSpring.entities

import com.fasterxml.jackson.annotation.JsonInclude
import com.sun.istack.NotNull
import org.springframework.boot.convert.DataSizeUnit
import java.io.Serializable
import javax.persistence.*

@Entity
@Table (name = "tb_User")
data class User(
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @JsonInclude(JsonInclude.Include.NON_NULL)
     private val id: Long?,

     @Column (name = "Name")
     private var name: String = "",

     @Column (name = "Email")
     private var email: String = "",

     @Column (name = "Cpf")
     private var cpf: String = ""

     ) : Serializable {

     fun getId() = this.id


     fun getName() = this.name
     fun setName(name: String) {
        this.name = name
     }

     fun getEmail() = this.email
     fun setEmail(email: String) {
          this.email = email
     }

     fun getCpf() = this.cpf
     fun setCpf(cpf: String) {
          this.cpf = cpf
     }






}