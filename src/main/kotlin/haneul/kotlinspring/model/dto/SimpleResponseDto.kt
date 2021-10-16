package haneul.kotlinspring.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class SimpleResponseDto (
    val id: Long,
    val title: String,
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone="Asia/Seoul"
    )
    val createdAt: LocalDateTime,
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone="Asia/Seoul"
    )
    val updatedAt: LocalDateTime
)