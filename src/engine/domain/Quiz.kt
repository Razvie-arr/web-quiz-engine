package engine.domain

data class Quiz(val id: Long, val title: String, val text: String, val options: List<String>, val answer: List<Int>)

