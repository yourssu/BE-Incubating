package com.incubate.kotlinmemo.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.incubate.kotlinmemo.domain.Memo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MemoPreviewDto(
    val id:Long,
    val title:String,
    @JsonSerialize(using = DateSerializer::class)
    val createdAt:LocalDateTime,
    @JsonSerialize(using = DateSerializer::class)
    val updatedAt:LocalDateTime
)
//    constructor(memo:Memo){
//        val formatter:DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//        this.id = memo.id
//        this.title = memo.title
//        this.createdAt = memo.createdAt.format(formatter)
//        this.updatedAt = memo.updatedAt.format(formatter)
//    }
