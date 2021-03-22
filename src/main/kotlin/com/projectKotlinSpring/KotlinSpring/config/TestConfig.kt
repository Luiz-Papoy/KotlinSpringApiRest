package com.projectKotlinSpring.KotlinSpring.config

import com.projectKotlinSpring.KotlinSpring.entities.User
import com.projectKotlinSpring.KotlinSpring.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

//val dec =  DecimalFormat("#.00")

@Configuration
@Profile ("test")
class TestConfig : CommandLineRunner {

    @Autowired
    private lateinit var userRepository: UserRepository


    override fun run(vararg args: String?) {
        // Locale.setDefault(Locale.US)

        val user1 : User = User(null,"Luiz Fernando","lf132016@gmail.com","999484849")
        val user2 : User = User(null,"Paulo Fernandes","pf@gmail.com","347654378564")
        val user3 : User = User(null,"Ana Maria","an@gmail.com","6987697896")
        val user4 : User = User(null,"Delfim Junior","df@gmail.com","6456454546")
        val user5 : User = User(null,"Catia Oliveira","ca@gmail.com","11111111")
        val user6 : User = User(null,"Karina Martinelli","km@gmail.com","23232345455")

        userRepository.saveAll(listOf(user1,user2,user3,user4,user5,user6))

    }
}