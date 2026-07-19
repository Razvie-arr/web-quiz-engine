package engine.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "users",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_user_email", columnNames = ["email"])
    ]
)
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var email: String = "",
    @Column(nullable = false)
    var password: String = "",
)