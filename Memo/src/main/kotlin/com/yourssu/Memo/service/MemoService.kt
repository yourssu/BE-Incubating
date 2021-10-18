package com.yourssu.Memo.service

import com.yourssu.Memo.dtos.request.RequestCreateMemoDto
import com.yourssu.Memo.dtos.request.RequestUpdateMemoDto
import com.yourssu.Memo.dtos.response.ResponseCreateMemoDto
import com.yourssu.Memo.dtos.response.ResponseShowByPageMenuDto
import com.yourssu.Memo.dtos.response.ResponseShowMemoDto
import com.yourssu.Memo.dtos.response.ResponseUpdateMemoDto
import java.time.LocalDateTime
import java.util.*

open interface MemoService {
    fun saveMemo(memo : RequestCreateMemoDto) : ResponseCreateMemoDto

    fun updateMemo(id : Long, memo : RequestUpdateMemoDto) : ResponseUpdateMemoDto

    fun deleteMemo(id : Long)

    fun showMemoByPage(date : Date, page : Int) : ResponseShowByPageMenuDto

    fun showMemoById(id : Long) : ResponseShowMemoDto
}