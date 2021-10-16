package haneul.kotlinspring.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.BAD_REQUEST)
class MemoBadRequestException (
        message:String = "title/text를 입력해주세요."
) : RuntimeException(message)