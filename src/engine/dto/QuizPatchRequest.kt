package engine.dto

data class QuizPatchRequest(
    val title: String? = null,
    val text: String? = null,
    val options: List<String>? = null,
    val answer: List<Int>? = null
)