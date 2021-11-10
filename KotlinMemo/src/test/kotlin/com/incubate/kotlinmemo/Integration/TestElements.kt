package com.incubate.kotlinmemo.Integration

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

    val memoUpdateDto = MemoCreateUpdateDto("memo_update_title","memo_update_text")

    fun mapper(dto: MemoCreateUpdateDto):String{
        return objectMapper.writeValueAsString(dto)
    }

    val memo1 = MemoPreviewDto(1L,"memo1_title",LocalDateTime.now(),LocalDateTime.now())
    val memo2 = MemoPreviewDto(2L,"memo2_title",LocalDateTime.now(),LocalDateTime.now())
    val memo3 = MemoPreviewDto(3L,"memo3_title",LocalDateTime.now(),LocalDateTime.now())
    val memoList:List<MemoPreviewDto> = listOf(memo1,memo2,memo3)


}