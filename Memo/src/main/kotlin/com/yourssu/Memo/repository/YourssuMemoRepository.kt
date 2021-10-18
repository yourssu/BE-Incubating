package com.yourssu.Memo.repository

import com.yourssu.Memo.domain.Memo
import com.yourssu.Memo.dtos.request.RequestUpdateMemoDto
import com.yourssu.Memo.dtos.response.*
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager

@Repository
@RequiredArgsConstructor
class YourssuMemoRepository: MemoRepository {

    var em : EntityManager
    constructor(em : EntityManager){
        this.em = em;
    }
    override fun save(memo: Memo): ResponseCreateMemoDto {
        em.persist(memo)

        return ResponseCreateMemoDto(memo)
    }

    override fun update(id: Long, memo: RequestUpdateMemoDto): ResponseUpdateMemoDto {
        var updateMemo : Memo = em.find(Memo::class.java, id)
        updateMemo.title = memo.title
        updateMemo.text = memo.text
        updateMemo.updatedAt = Date()
        return ResponseUpdateMemoDto(updateMemo)
    }

    override fun delete(id: Long) {
        val deleteMemo = em.find(Memo::class.java, id)
        em.remove(deleteMemo)
    }

    override fun showByPage(searchDate: Date, page: Int): ResponseShowByPageMenuDto {
        val memoList = em.createQuery(
            "select m from Memo m where m.createdAt > :searchDate",
            Memo::class.java
        )
            .setParameter("searchDate", searchDate)
            .resultList
        Collections.reverse(memoList)

        val responseMemoList = ArrayList<ResponseMemo>()

        for (i in (page - 1) * 5..page * 5 - 1) {
            if (i >= memoList.size) {
                break
            }
            responseMemoList.add(ResponseMemo(memoList.get(i)))
        }
        return ResponseShowByPageMenuDto(responseMemoList)
    }

    override fun show(id: Long): ResponseShowMemoDto {
        val findMemo = em.find(Memo::class.java, id)
        return ResponseShowMemoDto(findMemo)
    }
}