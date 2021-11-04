package com.incubate.kotlinmemo.Integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import org.springframework.context.annotation.Bean
import java.time.Clock
import java.time.LocalDate

class IntegrationTestElements {
    val objectMapper: ObjectMapper = jacksonObjectMapper()

    val memoDto = MemoCreateUpdateDto("memo_title", "memo_text")

    fun mapper(dto: MemoCreateUpdateDto):String{
        return objectMapper.writeValueAsString(dto)
    }

    fun clock():Clock{
        return Clock.systemDefaultZone()
    }
//
//    fun someMethod():LocalDate{
//        return LocalDate.now(clock())
//    }

}