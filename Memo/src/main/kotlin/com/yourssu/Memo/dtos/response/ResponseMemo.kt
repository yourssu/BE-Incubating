package com.yourssu.Memo.dtos.response

import com.yourssu.Memo.domain.Memo
import java.text.SimpleDateFormat

val transFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

class ResponseMemo(memo : Memo) {

    val id = memo.id
    val title = memo.title
    val text = memo.text
    val createdAt : String = transFormat.format(memo.createdAt)
    val updatedAt : String = transFormat.format(memo.updatedAt)
}