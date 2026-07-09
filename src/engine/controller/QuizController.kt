package engine.controller

import engine.dto.AnswerRequest
import engine.dto.QuizCreateRequest
import engine.dto.QuizResponse
import engine.dto.SolveQuizResponse
import engine.exception.QuizNotFoundException
import engine.mapper.toResponse
import engine.service.QuizService
import engine.util.QuizMessages
import jakarta.validation.Valid
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
    fun createQuiz(@RequestBody @Valid request: QuizCreateRequest): QuizResponse {
        val quiz = quizService.createQuiz(
            title = request.title,
            text = request.text,
            options = request.options,
            answer = request.answer
        )
        return quiz.toResponse()
    }

    @PostMapping("/{id}/solve")
    fun solveQuiz(@PathVariable id: Long, @RequestBody answerRequest: AnswerRequest): SolveQuizResponse {
        val quiz = quizService.getQuizById(id) ?: throw QuizNotFoundException(id)
        val correct = quizService.checkAnswer(quiz, answerRequest.answer)
        val feedback = if (correct) QuizMessages.CORRECT_FEEDBACK else QuizMessages.INCORRECT_FEEDBACK
        return SolveQuizResponse(correct, feedback)
    }

}