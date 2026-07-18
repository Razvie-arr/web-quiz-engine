package engine.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class QuizOwnershipException(quizId: Long, email: String) :
    RuntimeException("User $email is not allowed to delete this quiz with id $quizId.")