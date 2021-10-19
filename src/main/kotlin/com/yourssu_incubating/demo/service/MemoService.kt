package com.yourssu_incubating.demo.service

import com.yourssu_incubating.demo.controller.exception.MemoNotExistException
import com.yourssu_incubating.demo.controller.request.SaveMemoRequest
import com.yourssu_incubating.demo.controller.request.UpdateMemoRequest
import com.yourssu_incubating.demo.controller.response.MemoResponse
import com.yourssu_incubating.demo.entity.memo.Memos
import com.yourssu_incubating.demo.entity.memo.MemosRepository
import com.yourssu_incubating.demo.utils.FormatUtil.localDateTimeToString
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
        val memo = Memos(title = requestDto.title, text = requestDto.text)
        return this.memoRepository.save(memo)
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
    fun searchByDate(date: LocalDate, pageable: Pageable): List<MemoResponse> {
        val startDate = LocalDateTime.of(date, LocalTime.of(0, 0, 0))
        val endDate = LocalDateTime.of(date, LocalTime.of(23, 59, 59))

        val memosList: List<Memos> = this.memoRepository.findByCreatedAtBetween(startDate, endDate, pageable)
        var memoResponseList = mutableListOf<MemoResponse>()

        if (memosList.isEmpty()) return memoResponseList

        for (memo: Memos in memosList) {
            memoResponseList.add(
                MemoResponse(
                    memo.id,
                    memo.title,
                    memo.text,
                    localDateTimeToString(memo.createdAt),
                    localDateTimeToString(memo.updatedAt)
                )
            )
        }

        return memoResponseList
    }
}