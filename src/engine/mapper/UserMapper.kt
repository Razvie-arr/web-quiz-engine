package engine.mapper

import engine.domain.User
import engine.dto.UserResponse
import engine.entity.UserEntity

fun UserEntity.toDomain() = User(
    id = requireNotNull(this.id),
    email = this.email,
    password = this.password
)

fun User.toEntity() = UserEntity(
    email = this.email,
    password = this.password
)

fun User.toResponse() = UserResponse(
    id = requireNotNull(this.id),
    email = this.email
)