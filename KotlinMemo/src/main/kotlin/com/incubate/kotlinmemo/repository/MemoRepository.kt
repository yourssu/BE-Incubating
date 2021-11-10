package com.incubate.kotlinmemo.repository

import com.incubate.kotlinmemo.domain.Memo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
@Repository
interface MemoRepository:JpaRepository<Memo,Long>{
    @Query("select m from Memo m where m.createdAt >= :date order by m.createdAt desc")
    fun getMemoAfterCreatedAtByPaging(@Param("date") date:LocalDateTime, paging: Pageable):Page<Memo>
    fun findByTitleAndText(title:String, text:String):Memo?

}