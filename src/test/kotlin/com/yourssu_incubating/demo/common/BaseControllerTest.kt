package com.yourssu_incubating.demo.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.yourssu_incubating.demo.entity.memo.Memos
import com.yourssu_incubating.demo.entity.memo.MemosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class BaseControllerTest(
    protected val mockMvc: MockMvc,
    protected val objectMapper: ObjectMapper,
    protected val memosRepository: MemosRepository,
) {
    val memoId: Long = 1
    val title = "title"
    val text = "text"

    fun generateMemo(): Memos {
        return memosRepository.save(
            Memos(
                memoId,
                title,
                text
            )
        )
    }
}