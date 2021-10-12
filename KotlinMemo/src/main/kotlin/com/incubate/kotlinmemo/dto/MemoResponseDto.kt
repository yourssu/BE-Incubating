package com.incubate.kotlinmemo.dto

import com.incubate.kotlinmemo.domain.Memo
import lombok.Getter
import java.time.format.DateTimeFormatter

class MemoResponseDto {
    val id:Long
    val title:String
    val text:String
    val updatedAt:String
    val createdAt:String

    constructor(memo: Memo){
        val dateTimeFormatter:DateTimeFormatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        this.id = memo.id
        this.title = memo.title
        this.text = memo.text
        this.updatedAt = memo.updatedAt.format(dateTimeFormatter)
        this.createdAt = memo.createdAt.format(dateTimeFormatter)
    }
}