package com.yourssu_incubating.demo.common

import com.yourssu_incubating.demo.entity.memo.MemosRepository
import com.yourssu_incubating.demo.service.MemoService
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@AutoConfigureMockMvc
@SpringBootTest
class BaseServiceTest {

    @InjectMocks
    protected lateinit var memoService: MemoService

    @Mock
    protected lateinit var memosRepository: MemosRepository
}