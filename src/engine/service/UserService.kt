package engine.service

import engine.domain.User
import engine.exception.UserAlreadyExistsException
import engine.persistence.UserRepository
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    fun findUserByEmail(email: String) = userRepository.findByEmail(email)

    @Transactional
    fun createUser(email: String, rawPassword: String): User {
        if (userRepository.existsByEmail(email)) {
            throw UserAlreadyExistsException(email)
        }

        val encodedPassword = passwordEncoder.encode(rawPassword)
        val user = User(email = email, password = encodedPassword)
        return userRepository.create(user)
    }

}