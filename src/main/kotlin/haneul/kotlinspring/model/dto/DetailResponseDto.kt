package haneul.kotlinspring.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class DetailResponseDto (
        val id: Long,
        val title: String,
        val text: String,
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