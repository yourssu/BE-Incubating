package com.incubate.kotlinmemo.service

import com.incubate.kotlinmemo.domain.Memo
import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import com.incubate.kotlinmemo.dto.MemoPreviewDto
import com.incubate.kotlinmemo.dto.MemoResponseDto
import com.incubate.kotlinmemo.repository.MemoRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
import javax.transaction.Transactional
import kotlin.collections.ArrayList


@Transactional
open class MemoServiceImpl(private val memoRepository: MemoRepository):MemoService {
    override fun createMemo(memo: MemoCreateUpdateDto): MemoResponseDto {
        if (memoRepository.checkDuplicatedTitleText(memo)){
            throw java.lang.IllegalStateException("이미 동일한 메모가 존재합니다.")
        } else {
            val createdMemo: Memo = Memo(title = memo.title, text = memo.text)

            val save = memoRepository.save(createdMemo)
            val responseMemo: MemoResponseDto = MemoResponseDto(save.id,save.title,save.text,save.createdAt,save.updatedAt)
            return responseMemo
        }
    }

    override fun updateMemo(memo_id: Long, memo: MemoCreateUpdateDto): MemoPreviewDto {
        if(memoRepository.checkDuplicatedTitleText(memo)){
            throw java.lang.IllegalStateException("이미 동일한 메모가 존재합니다.")
        } else {
            val memo1: Memo = memoRepository.update(memo, memo_id)

            val responseMemo: MemoPreviewDto = MemoPreviewDto(memo1.id,memo1.title,memo1.createdAt,memo1.updatedAt)
            return responseMemo
        }
    }

    override fun deleteMemo(memo_id: Long) {
        memoRepository.delete(memo_id)
    }

    override fun getMemoAfterDate(date: LocalDate, page: Int): List<MemoPreviewDto> {
        var memoList:List<Memo> = memoRepository.getMemoAfterDate(date,page)
        val memos:ArrayList<MemoPreviewDto> = ArrayList<MemoPreviewDto>()
        memoList.forEach{
            memo -> var memoPreview = MemoPreviewDto(memo.id,memo.title,memo.createdAt,memo.updatedAt)
            memos.add(memoPreview)
        }
        return memos
    }

    override fun MemoInfo(memo_id: Long): MemoResponseDto {
        val memo: Memo = memoRepository.findById(memo_id)

        if(memo != null){
            return MemoResponseDto(memo.id,memo.title,memo.text,memo.createdAt,memo.updatedAt)
        } else throw IllegalStateException("해당하는 메모가 존재하지 않습니다.")
    }


}