package engine.domain

data class User(
    val id: Long? = null,
    val email: String,
    val password: String
)
