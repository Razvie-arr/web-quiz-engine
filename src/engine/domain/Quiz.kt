package engine.domain

data class Quiz(
    val id: Long? = null,
    val title: String,
    val text: String,
    val options: List<String>,
    val answer: List<Int>
) {

    fun isAnswerCorrect(inputAnswer: List<Int>) = this.answer.sorted() == inputAnswer.sorted()

}



