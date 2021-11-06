package com.yourssu_incubating.demo.service

import com.yourssu_incubating.demo.common.BaseServiceTest
import com.yourssu_incubating.demo.controller.request.SaveMemoRequest
import com.yourssu_incubating.demo.controller.request.UpdateMemoRequest
import com.yourssu_incubating.demo.entity.memo.Memos
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.any
import org.springframework.data.repository.findByIdOrNull

class MemoServiceTest: BaseServiceTest() {

    @Test
    fun saveMemoSuccess() {
        given(memosRepository.save(any()))
            .willReturn(Memos())

        val title = "title"
        val text = "text"

        val request = SaveMemoRequest(title = title, text = text)

        val result = memoService.saveMemo(request)

        assertNotNull(result);
    }

    @Test
    fun updateMemoTest() {
        given(memosRepository.findByIdOrNull(any()))
            .willReturn(Memos())

        val updatedTitle = "updated title"
        val updatedText = "updated text"

        val request = UpdateMemoRequest(title = updatedTitle, text = updatedText)
    }

    @Test
    fun getMemoTest() {
        given(memosRepository.findByIdOrNull(any()))
            .willReturn(Memos())
    }

    @Test
    fun deleteMemoTest() {
        given(memosRepository.findByIdOrNull(any()))
            .willReturn(Memos())
    }

    @Test
    fun searchByDateTest() {
        given(memosRepository.findByCreatedAtBetween(any(), any(), any()))
            .willReturn(mutableListOf<Memos>())

    }
}