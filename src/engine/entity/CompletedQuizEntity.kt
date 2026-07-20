package engine.entity

import jakarta.persistence.*
import kotlin.time.Instant

@Entity
@Table(name = "completed_quizzes")
class CompletedQuizEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    var quiz: QuizEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity? = null,

    var completedAt: Instant? = null
)