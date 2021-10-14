package com.yourssu_incubating.demo.controller

import com.yourssu_incubating.demo.common.BaseControllerTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime

class SearchByDateMemoTest: BaseControllerTest() {

    @Test
    @DisplayName("200: search memos by date")
    fun searchByDateTestSuccess() {
        generateMemo()
        generateMemo()

        val date = "2021-10-14"

        val test = mockMvc.get("/memos?date=$date&page=0", date) {
            contentType = MediaType.APPLICATION_JSON
        }

        test.andExpect {
            status { isOk() }
        }
    }

    @Test
    @DisplayName("400: search memos by date")
    fun searchByDateTestFail_BAD_REQUEST() {
        generateMemo()
        generateMemo()

        val date = "2021-10-1y"

        val test = mockMvc.get("/memos?date=$date&page=0", date) {
            contentType = MediaType.APPLICATION_JSON
        }

        test.andExpect {
            status { isBadRequest() }
        }
    }
}