package com.incubate.kotlinmemo.repository

import com.incubate.kotlinmemo.domain.Memo
import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import java.time.LocalDate
import java.util.*

interface MemoRepository {
    fun save(memo:Memo):Memo
    fun delete(memo_id:Long)
    fun MemoInfoByDate(date:LocalDate, page:Int):List<Memo>
    fun findById(memo_id:Long):Memo
    fun update(memo: MemoCreateUpdateDto, id:Long):Memo
    fun validationCheck(memo:MemoCreateUpdateDto):Boolean
}