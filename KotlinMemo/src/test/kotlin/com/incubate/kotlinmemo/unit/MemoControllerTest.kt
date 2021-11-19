package com.incubate.kotlinmemo.unit

import com.incubate.kotlinmemo.TestElements
import com.incubate.kotlinmemo.controller.MemoController
import com.incubate.kotlinmemo.dto.MemoPreviewDto
import com.incubate.kotlinmemo.dto.MemoResponseDto
import com.incubate.kotlinmemo.service.MemoService
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDate
import java.time.LocalDateTime


@WebMvcTest(controllers = [MemoController::class])
class MemoControllerTest {
        var memoService : MemoService = Mockito.spy(MemoService::class.java)

        val mvc: MockMvc = MockMvcBuilders.standaloneSetup(MemoController(memoService)).build()

        val integrationTestElements = TestElements()

        @Test
        fun createMemo(){
                given(memoService.createMemo(integrationTestElements.memoDto)).willReturn(MemoResponseDto(1L,"memo_title","memo_text",
                        LocalDateTime.now(), LocalDateTime.now()))

                mvc.perform(MockMvcRequestBuilders.post("/memos")
                        .contentType(MediaType.APPLICATION_JSON).content(integrationTestElements.mapper(integrationTestElements.memoDto)).accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("title").value("memo_title"))
                        .andExpect(MockMvcResultMatchers.jsonPath("text").value("memo_text"))
        }

        @Test
        fun updateMemo(){
                given(memoService.updateMemo(1L,integrationTestElements.memoUpdateDto)).willReturn(
                        MemoPreviewDto(1L,integrationTestElements.memoUpdateDto.title,
                        LocalDateTime.now(),integrationTestElements.memo.createdAt)
                )
                mvc.perform(MockMvcRequestBuilders.put("/memos/{memoId}",integrationTestElements.memo.id)
                        .contentType(MediaType.APPLICATION_JSON).content(integrationTestElements.mapper(integrationTestElements.memoUpdateDto)).accept(
                                MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk)
                        .andExpect(MockMvcResultMatchers.jsonPath("title").value("memo_update_title"))
        }

        @Test
        fun deleteMemo(){
                mvc.perform(MockMvcRequestBuilders.delete("/memos/{memoId}",integrationTestElements.memo.id))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk)
        }

        @Test
        fun memoInfo(){
                given(memoService.MemoInfo(1L)).willReturn(MemoResponseDto(1L,integrationTestElements.memo.title,integrationTestElements.memo.text,integrationTestElements.memo.updatedAt,integrationTestElements.memo.createdAt))

                mvc.perform(MockMvcRequestBuilders.get("/memos/{memoId}",integrationTestElements.memo.id))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk)
                        .andExpect(MockMvcResultMatchers.jsonPath("title").value("memo_title"))
                        .andExpect(MockMvcResultMatchers.jsonPath("text").value("memo_text"))
        }

        @Test
        fun getMemoAfterCreatedAtByPaging(){
                given(memoService.findByCreatedAtGreaterThanEqualOrderByCreatedAtDesc(LocalDate.of(2020,1,1),1)).willReturn(integrationTestElements.memoList)

                mvc.perform(MockMvcRequestBuilders.get("/memos?date=2020-01-01&page=1"))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk)
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("memo1_title"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("memo2_title"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[2].title").value("memo3_title"))

        }
}