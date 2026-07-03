package engine.controller

import engine.dto.QuizCreateRequest
import engine.dto.QuizResponse
import engine.dto.SolveQuizResponse
import engine.exception.QuizNotFoundException
import engine.service.QuizService
import engine.util.QuizMessages
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/quizzes")
class QuizController(private val quizService: QuizService) {

    @GetMapping("/{id}")
    fun getQuiz(@PathVariable id: Int): QuizResponse {
        val quiz = quizService.getQuizById(id) ?: throw QuizNotFoundException(id)
        return QuizResponse(
            id = quiz.id,
            title = quiz.title,
            text = quiz.text,
            options = quiz.options
        )
    }

    @GetMapping
    fun getQuizzes(): List<QuizResponse> {
        val quizzes = quizService.getAllQuizzes()
        return quizzes.map { QuizResponse(it.id, it.title, it.text, it.options) }
    }

    @PostMapping
    fun createQuiz(@RequestBody request: QuizCreateRequest): QuizResponse {
        val quiz = quizService.createQuiz(
            title = request.title,
            text = request.text,
            options = request.options,
            answer = request.answer
        )
        return QuizResponse(
            id = quiz.id,
            title = quiz.title,
            text = quiz.text,
            options = quiz.options,
        )
    }

    @PostMapping("/{id}/solve")
    fun solveQuiz(@PathVariable id: Int, @RequestParam answer: Int): SolveQuizResponse {
        val quiz = quizService.getQuizById(id) ?: throw QuizNotFoundException(id)
        val correct = quizService.checkAnswer(quiz, answer)
        val feedback = if (correct) QuizMessages.CORRECT_FEEDBACK else QuizMessages.INCORRECT_FEEDBACK
        return SolveQuizResponse(correct, feedback)
    }

}