package com.incubate.kotlinmemo.service

import com.incubate.kotlinmemo.domain.Memo
import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import com.incubate.kotlinmemo.dto.MemoPreviewDto
import com.incubate.kotlinmemo.dto.MemoResponseDto
import com.incubate.kotlinmemo.repository.MemoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.transaction.Transactional
import kotlin.collections.ArrayList

@Service
@Transactional
open class MemoServiceImpl(private val memoRepository: MemoRepository):MemoService {
    override fun createMemo(memo: MemoCreateUpdateDto): MemoResponseDto {
        if (memoRepository.findByTitleAndText(memo.title,memo.text) != null){
            throw java.lang.IllegalStateException("이미 동일한 메모가 존재합니다.")
        } else {
            val createdMemo: Memo = Memo(title = memo.title, text = memo.text)

            val save = memoRepository.save(createdMemo)
            val responseMemo: MemoResponseDto = MemoResponseDto(save.id,save.title,save.text,save.createdAt,save.updatedAt)
            return responseMemo
        }
    }

    override fun updateMemo(memo_id: Long, memo: MemoCreateUpdateDto): MemoPreviewDto {
        if(memoRepository.findByTitleAndText(memo.title, memo.text) != null){
            throw java.lang.IllegalStateException("이미 동일한 메모가 존재합니다.")
        } else {
            val memo1: Memo = memoRepository.findById(memo_id).get()
            memo1.title = memo.title
            memo1.text = memo.text
            memo1.updatedAt = LocalDateTime.now()

            val responseMemo: MemoPreviewDto = MemoPreviewDto(memo1.id,memo1.title,memo1.createdAt,memo1.updatedAt)
            return responseMemo
        }
    }

    override fun deleteMemo(memo_id: Long) {
        val memo:Memo = memoRepository.findById(memo_id).get()
        memoRepository.delete(memo)
    }

    override fun getMemoAfterCreatedAtByPaging(date: LocalDate, page: Int): List<MemoPreviewDto> {
        val dateTime = date.atTime(LocalTime.now())
        print("dateTime : "+dateTime)
        val pageRequest:PageRequest = PageRequest.of(page,5,)
        var memoList:Page<Memo> = memoRepository.getMemoAfterCreatedAtByPaging(dateTime,pageRequest)
        val memos:ArrayList<MemoPreviewDto> = ArrayList<MemoPreviewDto>()

        memoList.content.forEach{
            memo -> var memoPreview = MemoPreviewDto(memo.id,memo.title,memo.createdAt,memo.updatedAt)
            memos.add(memoPreview)
        }
        return memos
    }

    override fun MemoInfo(memo_id: Long): MemoResponseDto {
        val memo: Memo = memoRepository.findById(memo_id).get()

        if(memo != null){
            return MemoResponseDto(memo.id,memo.title,memo.text,memo.createdAt,memo.updatedAt)
        } else throw IllegalStateException("해당하는 메모가 존재하지 않습니다.")
    }


}