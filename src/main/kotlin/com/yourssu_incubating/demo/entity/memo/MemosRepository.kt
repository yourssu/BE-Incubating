package com.yourssu_incubating.demo.entity.memo

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime


interface MemosRepository: JpaRepository<Memos, Long> {
    fun findByCreatedAtBetween(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        pageable: Pageable
    ): List<Memos>
}