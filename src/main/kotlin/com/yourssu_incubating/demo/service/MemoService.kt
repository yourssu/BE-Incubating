package com.yourssu_incubating.demo.service

import com.yourssu_incubating.demo.controller.exception.MemoNotExistException
import com.yourssu_incubating.demo.controller.request.SaveMemoRequest
import com.yourssu_incubating.demo.controller.request.UpdateMemoRequest
import com.yourssu_incubating.demo.controller.response.MemoResponse
import com.yourssu_incubating.demo.entity.memo.Memos
import com.yourssu_incubating.demo.entity.memo.MemosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull

import java.time.LocalTime
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class MemoService {

    @Autowired
    private lateinit var memoRepository: MemosRepository

    @Transactional
    fun saveMemo(requestDto: SaveMemoRequest): Memos {
        return this.memoRepository.save(requestDto.toEntity())
    }

    @Transactional
    fun updateMemo(memoId: Long, requestDto: UpdateMemoRequest): Memos {
        val memo: Memos = memoRepository.findByIdOrNull(memoId) ?: throw MemoNotExistException()
        memo.update(requestDto.title, requestDto.text)

        return memo
    }

    @Transactional(readOnly = true)
    fun getMemo(memoId: Long): Memos {
        return memoRepository.findByIdOrNull(memoId) ?: throw MemoNotExistException()
    }

    @Transactional
    fun deleteMemo(memoId: Long) {
        val memo: Memos = memoRepository.findByIdOrNull(memoId) ?: throw MemoNotExistException()
        memoRepository.delete(memo)
    }

    @Transactional(readOnly = true)
    fun searchByDate(date: String, pageable: Pageable): List<MemoResponse> {
        val startDate = LocalDateTime.of(LocalDate.parse(date), LocalTime.of(0, 0, 0))
        val endDate = LocalDateTime.of(LocalDate.parse(date), LocalTime.of(23, 59, 59))

        val memosList: List<Memos> = this.memoRepository.findByCreatedAtBetween(startDate, endDate, pageable)
        var memoResponseList = mutableListOf<MemoResponse>()

        if (memosList.isEmpty()) return memoResponseList

        for (memo: Memos in memosList) {
            memoResponseList.add(MemoResponse(memo.id, memo.title, memo.text, memo.getStringCreatedAt(), memo.getStringUpdatedAt()))
        }

        return memoResponseList
    }
}