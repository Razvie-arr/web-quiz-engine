package engine.entity

import jakarta.persistence.*


@Entity
@Table(name = "quizzes")
class QuizEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String = "",
    var text: String = "",

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "quiz_options", joinColumns = [JoinColumn(name = "quiz_id")])
    var options: MutableList<String> = mutableListOf(),

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "quiz_answers", joinColumns = [JoinColumn(name = "quiz_id")])
    var answer: MutableList<Int> = mutableListOf(),

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    var author: UserEntity? = null
)