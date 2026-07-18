package engine.security

import engine.entity.UserEntity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(private val userEntity: UserEntity) : UserDetails {

    override fun getAuthorities() = listOf(SimpleGrantedAuthority("ROLE_USER"))

    override fun getPassword() = userEntity.password

    override fun getUsername() = userEntity.email

}