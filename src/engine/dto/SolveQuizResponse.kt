package engine.dto

import engine.util.CORRECT_FEEDBACK
import engine.util.INCORRECT_FEEDBACK

data class SolveQuizResponse(
    val success: Boolean,
    val feedback: String = if (success) CORRECT_FEEDBACK else INCORRECT_FEEDBACK
)