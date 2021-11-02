package com.yourssu_incubating.demo.entity

import com.yourssu_incubating.demo.entity.memo.Memos
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MemoEntityTest {

    @Test
    fun memoEntityTest() {
        val title = "title"
        val text = "text"

        val memo = Memos(title = title, text = text)

        assertThat(memo.title).isEqualTo(title)
        assertThat(memo.text).isEqualTo(text)
    }
}