package com.incubate.kotlinmemo.Integration

import com.incubate.kotlinmemo.TestElements
import com.incubate.kotlinmemo.dto.MemoPreviewDto
import com.incubate.kotlinmemo.dto.MemoResponseDto
import com.incubate.kotlinmemo.repository.MemoRepository
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity.post
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.EntityManager
import javax.transaction.Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemoIntegrationTest{

    @Autowired
    lateinit var  memoRepository: MemoRepository
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var webApplicationContext: WebApplicationContext
    private val testElements:TestElements = TestElements()

    @BeforeEach
    fun beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
        memoRepository.deleteAll()
    }

    @org.junit.jupiter.api.Test
    fun createMemo(){
        mockMvc.perform(
            MockMvcRequestBuilders.post("/memos")
            .contentType(MediaType.APPLICATION_JSON).content(testElements.mapper(testElements.memoDto)).accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("title").value("memo_title"))
            .andExpect(MockMvcResultMatchers.jsonPath("text").value("memo_text"))
    }

    @org.junit.jupiter.api.Test
    fun updateMemo(){
        memoRepository.save(testElements.memo)

        mockMvc.perform(MockMvcRequestBuilders.put("/memos/{memoId}",2)
            .contentType(MediaType.APPLICATION_JSON).content(testElements.mapper(testElements.memoUpdateDto)).accept(
                MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("title").value("memo_update_title"))
    }

    @org.junit.jupiter.api.Test
    fun deleteMemo(){
        val savedMemo=memoRepository.save(testElements.memo)

        mockMvc.perform(MockMvcRequestBuilders.delete("/memos/{memoId}",savedMemo.id))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @org.junit.jupiter.api.Test
    fun memoInfo(){
        memoRepository.save(testElements.memo)
        mockMvc.perform(MockMvcRequestBuilders.get("/memos/{memoId}",testElements.memo.id))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("title").value("memo_title"))
            .andExpect(MockMvcResultMatchers.jsonPath("text").value("memo_text"))
    }

    @org.junit.jupiter.api.Test
    fun getMemoAfterCreatedAtByPaging(){
        val savedMemo1 =memoRepository.save(testElements.memo1)
        memoRepository.save(testElements.memo2)
        memoRepository.save(testElements.memo3)
        val pageRequest: PageRequest = PageRequest.of(1,5)

        val memoList = memoRepository.findByCreatedAtGreaterThanEqualOrderByCreatedAtDesc(LocalDateTime.of(2020,1,1,0,0),pageRequest)
        print("memoList 는 "+memoList.content)

        print("savedMemo1 : "+ savedMemo1)
        mockMvc.perform(MockMvcRequestBuilders.get("/memos").param("date","2020-01-01").param("page","1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("memo1_title"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("memo2_title"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].title").value("memo3_title"))

    }

}