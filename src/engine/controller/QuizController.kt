package engine.controller

import engine.dto.QuizAnswerResponse
import engine.dto.QuizQuestionResponse
import engine.service.QuizService
import engine.util.QuizMessages
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/quiz")
class QuizController(val quizService: QuizService) {

    @GetMapping
    fun getQuizQuestion(): QuizQuestionResponse {
        val question = quizService.getQuizQuestion()
        return QuizQuestionResponse(question.title, question.text, question.options)
    }

    @PostMapping
    fun getAnswerFeedback(@RequestParam answer: Int): QuizAnswerResponse {
        val question = quizService.getQuizQuestion()
        val correct = quizService.checkAnswer(question, answer)
        val feedback = if (correct) QuizMessages.CORRECT_FEEDBACK else QuizMessages.INCORRECT_FEEDBACK
        return QuizAnswerResponse(correct, feedback)
    }

}