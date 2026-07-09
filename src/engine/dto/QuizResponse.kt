package engine.dto

import engine.domain.Quiz

data class QuizResponse(val id: Int, val title: String, val text: String, val options: List<String>)

fun Quiz.toResponse() = QuizResponse(
    id = this.id,
    title = this.title,
    text = this.text,
    options = this.options
)