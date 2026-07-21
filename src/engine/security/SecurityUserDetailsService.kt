package engine.security

import engine.persistence.JpaUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SecurityUserDetailsService(private val repository: JpaUserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails? {
        val user = repository.findByEmail(email) ?: throw UsernameNotFoundException("User not found")
        return SecurityUser(user)
    }

}