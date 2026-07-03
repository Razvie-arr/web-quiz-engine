package engine.domain

data class Quiz(val id: Int, val title: String, val text: String, val options: List<String>, val answer: Int)

