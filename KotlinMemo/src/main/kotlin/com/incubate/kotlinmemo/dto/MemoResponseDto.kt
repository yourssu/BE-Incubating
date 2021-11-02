package com.incubate.kotlinmemo.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDateTime

class MemoResponseDto (
    val id:Long,
    val title:String,
    val text:String,
    @JsonSerialize(using = DateSerializer::class)
    val updatedAt:LocalDateTime,
    @JsonSerialize(using = DateSerializer::class)
    val createdAt:LocalDateTime
)
