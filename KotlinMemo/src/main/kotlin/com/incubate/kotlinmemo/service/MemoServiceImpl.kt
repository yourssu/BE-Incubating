package com.incubate.kotlinmemo.service

import com.incubate.kotlinmemo.domain.Memo
import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import com.incubate.kotlinmemo.dto.MemoPreviewDto
import com.incubate.kotlinmemo.dto.MemoResponseDto
import com.incubate.kotlinmemo.repository.MemoRepository
import java.time.LocalDate
import java.util.*
import javax.transaction.Transactional
import kotlin.collections.ArrayList


@Transactional
open class MemoServiceImpl(private val memoRepository: MemoRepository):MemoService {

    override fun createMemo(memo: MemoCreateUpdateDto): MemoResponseDto {
        var createdMemo:Memo = Memo()
        createdMemo.title = memo.title
        createdMemo.text = memo.text
        val save = memoRepository.save(createdMemo)

        val responseMemo:MemoResponseDto = MemoResponseDto(save)
        return responseMemo
    }

    override fun updateMemo(memo_id: Long, memo: MemoCreateUpdateDto): MemoPreviewDto {
        val memo1:Memo = memoRepository.update(memo, memo_id)

        val responseMemo: MemoPreviewDto = MemoPreviewDto(memo1)
        return responseMemo

    }

    override fun deleteMemo(memo_id: Long) {
        memoRepository.delete(memo_id)
    }

    override fun MemoInfoByDate(date: LocalDate, page: Int): List<MemoPreviewDto> {
        var memoList:List<Memo> = memoRepository.MemoInfoByDate(date,page)
        val memos:ArrayList<MemoPreviewDto> = ArrayList<MemoPreviewDto>()
        memoList.forEach{
            memo -> var memoPreview = MemoPreviewDto(memo)
            memos.add(memoPreview)
        }
        return memos
    }

    override fun MemoInfo(memo_id: Long): MemoResponseDto {
        val memo: Optional<Memo> = memoRepository.findById(memo_id)
        if(memo.isPresent()){
            val existMemo:Memo = memo.get()
            return MemoResponseDto(existMemo)
        } else throw IllegalStateException("해당하는 메모가 존재하지 않습니다.")
    }


}