package haneul.kotlinspring.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class MemoNotFoundException(
        message:String = "존재하지 않는 메모입니다."
) : RuntimeException(message)