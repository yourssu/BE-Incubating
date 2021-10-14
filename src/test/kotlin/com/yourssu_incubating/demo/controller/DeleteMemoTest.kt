package com.yourssu_incubating.demo.controller

import com.yourssu_incubating.demo.common.BaseControllerTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.delete

class DeleteMemoTest: BaseControllerTest() {

    @Test
    @DisplayName("200: delete memo")
    fun deleteMemoSuccess() {
        generateMemo()

        val test = mockMvc.delete("/memos/$memoId", memoId)
        test.andExpect {
            status { isOk() }
        }
    }

    @Test
    @DisplayName("404: delete memo")
    fun deleteMemoFail_NOT_FOUND() {
        val test = mockMvc.delete("/memos/$memoId", memoId)
        test.andExpect {
            status { isNotFound() }
        }
    }
}