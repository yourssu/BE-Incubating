package com.yourssu_incubating.demo.controller.request

import com.yourssu_incubating.demo.entity.memo.Memos
import javax.validation.constraints.NotNull

class SaveMemoRequest (
    @field: NotNull
    var title: String,

    @field: NotNull
    var text: String,
) {
    fun toEntity(): Memos {
        return Memos(title = title, text = text)
    }
}