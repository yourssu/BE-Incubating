package com.incubate.kotlinmemo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.incubate.kotlinmemo.controller.MemoController
import com.incubate.kotlinmemo.domain.Memo
import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import com.incubate.kotlinmemo.dto.MemoResponseDto
import com.incubate.kotlinmemo.service.MemoService
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders



@WebMvcTest(controllers = [MemoController::class])
class MemoIntegrationTest {
        var memoService : MemoService = Mockito.spy(MemoService::class.java)

        val mvc: MockMvc = MockMvcBuilders.standaloneSetup(MemoController(memoService)).build()

        val objectMapper: ObjectMapper = jacksonObjectMapper()

        @Test
        fun createMemo(){
                given(memoService.createMemo(memoDto())).willReturn(MemoResponseDto(memo()))

                mvc.perform(MockMvcRequestBuilders.post("/memos")
                        .contentType(MediaType.APPLICATION_JSON).content(mapper(memoDto())).accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("title").value("memo_title"))
                        .andExpect(MockMvcResultMatchers.jsonPath("text").value("memo_text"))
        }

        private fun mapper(dto:MemoCreateUpdateDto):String{
                return objectMapper.writeValueAsString(dto)
        }
        private fun memoDto():MemoCreateUpdateDto{
                return MemoCreateUpdateDto("memo_title", "memo_text")
        }
        private fun memo():Memo{
                return Memo(1L, "memo_title", "memo_text")
        }
}