package com.incubate.kotlinmemo.dto

import com.incubate.kotlinmemo.domain.Memo
import lombok.EqualsAndHashCode
import java.time.format.DateTimeFormatter


class MemoPreviewDto {
    val id:Long
    val title:String
    val createdAt:String
    val updatedAt:String

    constructor(memo:Memo){
        val formatter:DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        this.id = memo.id
        this.title = memo.title
        this.createdAt = memo.createdAt.format(formatter)
        this.updatedAt = memo.updatedAt.format(formatter)
    }
}