package engine.controller

import engine.dto.AnswerRequest
import engine.dto.QuizCreateRequest
import engine.dto.QuizResponse
import engine.dto.SolveQuizResponse
import engine.exception.QuizNotFoundException
import engine.mapper.toResponse
import engine.service.QuizService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/quizzes")
class QuizController(private val quizService: QuizService) {

    @GetMapping("/{id}")
    fun getQuiz(@PathVariable id: Long): QuizResponse {
        val quiz = quizService.getQuizById(id) ?: throw QuizNotFoundException(id)
        return quiz.toResponse()
    }

    @GetMapping
    fun getQuizzes(): List<QuizResponse> {
        val quizzes = quizService.getAllQuizzes()
        return quizzes.map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createQuiz(
        @RequestBody @Valid request: QuizCreateRequest,
        @AuthenticationPrincipal details: UserDetails
    ): QuizResponse {
        val quiz = quizService.createQuiz(
            title = request.title,
            text = request.text,
            options = request.options,
            answer = request.answer,
            email = details.username
        )
        return quiz.toResponse()
    }

    @PostMapping("/{id}/solve")
    fun solveQuiz(@PathVariable id: Long, @RequestBody answerRequest: AnswerRequest): SolveQuizResponse {
        val quiz = quizService.getQuizById(id) ?: throw QuizNotFoundException(id)
        return SolveQuizResponse(quiz.isAnswerCorrect(answerRequest.answer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteQuiz(@PathVariable id: Long, @AuthenticationPrincipal details: UserDetails) {
        quizService.deleteQuizAsUser(id, details.username)
    }
}