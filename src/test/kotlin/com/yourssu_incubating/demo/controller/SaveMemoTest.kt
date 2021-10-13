package com.yourssu_incubating.demo.controller

import com.yourssu_incubating.demo.common.BaseControllerTest
import com.yourssu_incubating.demo.controller.request.SaveMemoRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

class SaveMemoTest: BaseControllerTest() {

    @Test
    @DisplayName("save memo")
    fun saveMemoSuccess() {
        val request = SaveMemoRequest("title", "text")
        val test = mockMvc.post("/memos/") {
            content = objectMapper.writeValueAsString(request)
            contentType = MediaType.APPLICATION_JSON
        }
        test.andExpect {
            status { isCreated() }
            jsonPath("title") { request.title }
            jsonPath("text") { request.text }
        }
    }
}