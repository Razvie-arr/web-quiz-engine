package engine.controller

import engine.dto.UserRegistrationRequest
import engine.dto.UserResponse
import engine.mapper.toResponse
import engine.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/api/register")
    fun register(@RequestBody @Valid request: UserRegistrationRequest): UserResponse {
        val user = userService.createUser(request.email, request.password)
        return user.toResponse()
    }

}