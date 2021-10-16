package com.yourssu.Memo.dtos.response

import com.yourssu.Memo.domain.Memo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.Lob

class ResponseCreateMemoDto(memo :  Memo) {
    val memo = ResponseMemo(memo)
}