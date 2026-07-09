package engine.entity

import jakarta.persistence.*


@Entity
@Table(name = "quiz")
class QuizEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String = "",
    var text: String = "",

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "quiz_options", joinColumns = [JoinColumn(name = "quiz_id")])
    var options: List<String> = mutableListOf(),

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "quiz_answer", joinColumns = [JoinColumn(name = "quiz_id")])
    var answer: List<Int> = mutableListOf(),
)