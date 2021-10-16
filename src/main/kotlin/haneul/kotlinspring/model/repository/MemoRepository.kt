package haneul.kotlinspring.model.repository

import haneul.kotlinspring.model.entity.Memo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface MemoRepository : JpaRepository<Memo, Long> {
    override fun findAll(pageable: Pageable): Page<Memo>
    fun findAllByCreatedAtBetween(start:LocalDateTime, end:LocalDateTime, pageable: Pageable) : Page<Memo>
}