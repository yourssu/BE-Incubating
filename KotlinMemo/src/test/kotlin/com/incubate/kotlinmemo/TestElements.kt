package com.incubate.kotlinmemo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.incubate.kotlinmemo.domain.Memo
import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import com.incubate.kotlinmemo.dto.MemoPreviewDto
import com.incubate.kotlinmemo.dto.MemoResponseDto
import org.springframework.context.annotation.Bean
import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime

class TestElements {
    val objectMapper: ObjectMapper = jacksonObjectMapper()

    val memoDto = MemoCreateUpdateDto("memo_title", "memo_text")

    val memo = Memo(1L, "memo_title","memo_text")
    val memo1 = Memo(0L,"memo1_title","memo1_text", LocalDateTime.now(),LocalDateTime.now())
    val memo2 = Memo(0L,"memo2_title","memo2_text",LocalDateTime.now(),LocalDateTime.now())
    val memo3 = Memo(0L,"memo3_title","memo3_text", LocalDateTime.now(),LocalDateTime.now())
    val memoUpdateDto = MemoCreateUpdateDto("memo_update_title","memo_update_text")

    fun mapper(dto: MemoCreateUpdateDto):String{
        return objectMapper.writeValueAsString(dto)
    }

    val memoDto1 = MemoPreviewDto(2L,"memo1_title",LocalDateTime.now(),LocalDateTime.now())
    val memoDto2 = MemoPreviewDto(3L,"memo2_title",LocalDateTime.now(),LocalDateTime.now())
    val memoDto3 = MemoPreviewDto(4L,"memo3_title",LocalDateTime.now(),LocalDateTime.now())
    val memoList:List<MemoPreviewDto> = listOf(memoDto1,memoDto2,memoDto3)


}