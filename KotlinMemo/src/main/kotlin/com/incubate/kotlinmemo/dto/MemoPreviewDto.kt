package com.incubate.kotlinmemo.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDateTime


class MemoPreviewDto(
    val id:Long,
    val title:String,
    @JsonSerialize(using = DateSerializer::class)
    val createdAt:LocalDateTime,
    @JsonSerialize(using = DateSerializer::class)
    val updatedAt:LocalDateTime
)

