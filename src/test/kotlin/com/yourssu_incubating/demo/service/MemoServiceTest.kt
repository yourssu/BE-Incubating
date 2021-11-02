package com.yourssu_incubating.demo.service

import com.yourssu_incubating.demo.entity.memo.MemosRepository
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@AutoConfigureMockMvc
@SpringBootTest
class MemoServiceTest(
    private val memoService: MemoService,
    private val memosRepository: MemosRepository,
) {

    @Test
    fun saveMemoTest() {

    }
}