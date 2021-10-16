package com.yourssu.Memo.repository

import com.yourssu.Memo.domain.Memo
import com.yourssu.Memo.dtos.request.RequestUpdateMemoDto
import com.yourssu.Memo.dtos.response.ResponseCreateMemoDto
import com.yourssu.Memo.dtos.response.ResponseShowByPageMenuDto
import com.yourssu.Memo.dtos.response.ResponseShowMemoDto
import com.yourssu.Memo.dtos.response.ResponseUpdateMemoDto
import java.time.LocalDateTime
import java.util.*


open interface MemoRepository {

    fun save(memo : Memo) : ResponseCreateMemoDto

    fun update(id : Long, memo : RequestUpdateMemoDto) : ResponseUpdateMemoDto

    fun delete(id : Long)

    fun showByPage(searchDate : Date, page : Int) : ResponseShowByPageMenuDto

    fun show(id : Long) : ResponseShowMemoDto
}