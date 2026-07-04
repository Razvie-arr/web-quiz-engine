package engine.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class QuizCreateRequest(
    @NotBlank(message = "Title shouldn't be blank.")
    val title: String,
    @NotBlank(message = "Message shouldn't be blank")
    val text: String,
    @NotNull(message = "Quiz should have options.")
    @Size(min = 2, message = "At least two options are required.")
    val options: List<String>,
    val answer: List<Int> = emptyList()
)
