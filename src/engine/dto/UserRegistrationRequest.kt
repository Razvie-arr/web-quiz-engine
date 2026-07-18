package engine.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

data class UserRegistrationRequest(
    @NotNull
    @Email
    val email: String,
    
    @NotBlank
    @Size(min = 5)
    val password: String
)
