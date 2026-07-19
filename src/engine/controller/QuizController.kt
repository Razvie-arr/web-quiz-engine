package engine.controller

import engine.dto.*
import engine.exception.QuizNotFoundException
import engine.mapper.toResponse
import engine.service.QuizService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
    fun getQuizzes(
        @PageableDefault(page = 0, size = 10) pageable: Pageable
    ): Page<QuizResponse> {
        val pagedQuizzes = quizService.getAllQuizzes(pageable)
        return pagedQuizzes.map { it.toResponse() }
    }

    @PostMapping
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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateQuiz(
        @PathVariable id: Long,
        @AuthenticationPrincipal details: UserDetails,
        @RequestBody @Valid request: QuizUpdateRequest
    ) {
        quizService.updateQuiz(id, request.title, request.text, request.options, request.answer, details.username)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun patchQuiz(
        @PathVariable id: Long,
        @AuthenticationPrincipal details: UserDetails,
        @RequestBody request: QuizPatchRequest
    ) {
        quizService.patchQuiz(
            quizId = id,
            title = request.title,
            text = request.text,
            options = request.options,
            answer = request.answer,
            email = details.username
        )
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteQuiz(@PathVariable id: Long, @AuthenticationPrincipal details: UserDetails) {
        quizService.deleteQuizAsUser(id, details.username)
    }
}