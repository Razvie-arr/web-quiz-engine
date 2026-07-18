package engine.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

data class UserRegistrationRequest(
    @NotNull
    @Email
    @Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$",
        message = "Email must contain a valid domain extension (e.g. .com)"
    )
    val email: String,

    @NotBlank
    @Size(min = 5)
    val password: String
)
