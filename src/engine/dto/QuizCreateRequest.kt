package engine.dto

data class QuizCreateRequest(val title: String, val text: String, val options: List<String>, val answer: Int)
