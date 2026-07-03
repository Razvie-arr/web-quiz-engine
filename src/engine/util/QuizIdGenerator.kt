package engine.util

import org.springframework.stereotype.Component

@Component
class QuizIdGenerator {

    private var currentId = 0

    fun generateId(): Int {
        currentId++
        return currentId
    }

}