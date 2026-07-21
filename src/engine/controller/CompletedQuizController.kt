package engine.controller

import engine.dto.CompletedQuizResponse
import engine.mapper.toResponse
import engine.service.CompletedQuizService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/quizzes/completed")
class CompletedQuizController(private val completedQuizService: CompletedQuizService) {

    @GetMapping
    fun getUserCompletedQuizzes(
        @PageableDefault(
            page = 0,
            size = 10,
            sort = ["completedAt"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable,
        @AuthenticationPrincipal details: UserDetails,
    ): Page<CompletedQuizResponse> {
        val pagedCompletedQuizzes = completedQuizService.getUserCompletedQuizzes(pageable, details.username)
        return pagedCompletedQuizzes.map { it.toResponse() }
    }

}