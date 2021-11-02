package com.yourssu_incubating.demo.controller

import com.yourssu_incubating.demo.common.BaseControllerTest
import com.yourssu_incubating.demo.controller.request.UpdateMemoRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.put

class UpdateMemoTest: BaseControllerTest() {

    @Test
    @DisplayName("200: update memo")
    fun updateMemoSuccess() {
        val request = UpdateMemoRequest("title", "updated Text")
        val test = mockMvc.put("/memos/{memoId}", memoId) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }

        test.andExpect {
            status { isOk() }
            jsonPath("title") { value("title") }
            jsonPath("text") { value("updated Text") }
        }
    }

    @Test
    @DisplayName("404: memo not found")
    fun updateMemoFail_NOT_FOUND() {
        val request = UpdateMemoRequest("title", "updated Text")

        val test = mockMvc.put("/memos/{memoId}", memoId) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }

        test.andExpect {
            status { isNotFound() }
        }
    }

    @Test
    @DisplayName("400: update memo request invalid")
    fun updateMemoFail_BAD_REQUEST() {
        val test = mockMvc.put("/memos/{memoId}", memoId) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString("title: title")
        }

        test.andExpect {
            status { isBadRequest() }
        }
    }
}