package com.yourssu.Memo.dtos.response

import com.yourssu.Memo.domain.Memo

class ResponseCreateMemoDto(memo :  Memo) {
    val memo = ResponseMemo(memo)
}