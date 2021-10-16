package com.yourssu.Memo.service

import com.yourssu.Memo.domain.Memo
import com.yourssu.Memo.dtos.request.RequestCreateMemoDto
import com.yourssu.Memo.dtos.request.RequestUpdateMemoDto
import com.yourssu.Memo.dtos.response.ResponseCreateMemoDto
import com.yourssu.Memo.dtos.response.ResponseShowByPageMenuDto
import com.yourssu.Memo.dtos.response.ResponseShowMemoDto
import com.yourssu.Memo.dtos.response.ResponseUpdateMemoDto
import com.yourssu.Memo.repository.MemoRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional()
class YourssuMemoService(private val memoRepository : MemoRepository) : MemoService{
    override fun saveMemo(memo: RequestCreateMemoDto): ResponseCreateMemoDto {

        val now = Date()

        var newMemo = Memo()

        newMemo.title = memo.title
        newMemo.text = memo.text
        newMemo.createdAt = now
        newMemo.updatedAt = now

        return memoRepository.save(newMemo)
    }

    override fun updateMemo(id: Long, memo: RequestUpdateMemoDto): ResponseUpdateMemoDto {
        return memoRepository.update(id, memo)
    }

    override fun deleteMemo(id: Long) {
        return memoRepository.delete(id)
    }

    override fun showMemoByPage(date: Date, page: Int) : ResponseShowByPageMenuDto {
        return memoRepository.showByPage(date, page)
    }

    override fun showMemoById(id: Long): ResponseShowMemoDto {
        return memoRepository.show(id)
    }
}