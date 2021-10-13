package com.yourssu_incubating.demo.service

import com.yourssu_incubating.demo.controller.request.SaveMemoRequest
import com.yourssu_incubating.demo.controller.request.UpdateMemoRequest
import com.yourssu_incubating.demo.entity.memo.Memos
import com.yourssu_incubating.demo.entity.memo.MemosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.domain.Pageable

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
        val memo: Memos = memoRepository.getOne(memoId)
        memo.update(requestDto.title, requestDto.text)

        return memo
    }

    @Transactional
    fun getMemo(memoId: Long): Memos {
        return memoRepository.getOne(memoId)
    }

    @Transactional
    fun deleteMemo(memoId: Long) {
        val memo: Memos = memoRepository.getOne(memoId)
        memoRepository.delete(memo)
    }

    @Transactional
    fun searchByDate(date: String, pageable: Pageable): List<Memos>? {
        val startDate = LocalDateTime.of(LocalDate.parse(date), LocalTime.of(0, 0, 0))
        val endDate = LocalDateTime.of(LocalDate.parse(date), LocalTime.of(23, 59, 59))

        val memosList: List<Memos> = this.memoRepository.findByCreatedAtBetween(startDate, endDate, pageable)

        if (memosList.isEmpty()) return memosList

        return memosList
    }
}