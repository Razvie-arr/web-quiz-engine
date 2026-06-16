package engine.domain

data class QuizQuestion(val title: String, val text: String, val options: List<String>, val correctOptionIndex: Int)

