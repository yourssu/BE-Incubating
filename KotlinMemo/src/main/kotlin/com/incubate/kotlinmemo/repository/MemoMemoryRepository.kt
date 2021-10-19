package com.incubate.kotlinmemo.repository

import com.incubate.kotlinmemo.domain.Memo
import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import javax.persistence.EntityManager


class MemoMemoryRepository(private val em:EntityManager): MemoRepository {

    override fun save(memo: Memo): Memo {
       em.persist(memo)
        return memo
    }

    override fun delete(memo_id: Long) {
       val memo = em.find(Memo::class.java , memo_id)
        em.remove(memo)
    }

    override fun MemoInfoByDate(date: LocalDate, page: Int): List<Memo> {
        val dateTime = date.atTime(LocalTime.now())
        val memoList:List<Memo> = em.createQuery("select m from Memo m where m.createdAt >= :date order by m.createdAt desc", Memo::class.java)
                .setParameter("date", dateTime)
                .setFirstResult((page-1) * 5)
                .setMaxResults(5)
                .resultList
        return memoList
    }

    override fun findById(memo_id: Long): Memo {
        val memo:Memo = em.find(Memo::class.java, memo_id)

        return memo
    }

    override fun update(memo: MemoCreateUpdateDto, id: Long): Memo {
        val memo1:Memo = em.find(Memo::class.java, id)
        memo1.title = memo.title
        memo1.text = memo.text
        memo1.updatedAt = LocalDateTime.now()
        return memo1
    }

    override fun validationCheck(memo:MemoCreateUpdateDto):Boolean{
        val check:Memo = em.createQuery("select m from Memo m where m.title = :title and m.text = :text", Memo::class.java)
            .setParameter("title", memo.title).setParameter("text",memo.text)
            .singleResult
        if (check == null) return false
        else return true
    }
}