package com.projectKotlinSpring.KotlinSpring.service.exceptions

import java.lang.RuntimeException
import javax.persistence.EntityNotFoundException

class ResourceNotFoundException (message: String) :
    RuntimeException("Resource Not found! This argument $message  not exist or was inserted incorrectly"){

}
