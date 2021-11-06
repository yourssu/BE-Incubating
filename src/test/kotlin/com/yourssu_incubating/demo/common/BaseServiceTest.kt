package com.yourssu_incubating.demo.common

import com.yourssu_incubating.demo.entity.memo.MemosRepository
import com.yourssu_incubating.demo.service.MemoService
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@ExtendWith(MockitoExtension::class)
open class BaseServiceTest {

    @Mock
    lateinit var memosRepository: MemosRepository

    @InjectMocks
    lateinit var memoService: MemoService
}