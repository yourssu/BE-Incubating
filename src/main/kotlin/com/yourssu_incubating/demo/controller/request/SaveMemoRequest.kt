package com.yourssu_incubating.demo.controller.request

import com.yourssu_incubating.demo.entity.memo.Memos
import javax.validation.constraints.NotNull

class SaveMemoRequest (
    @field: NotNull(message = "title is must be not null")
    var title: String,

    @field: NotNull(message = "text is must be not null")
    var text: String,
) {
    fun toEntity(): Memos {
        return Memos(title = title, text = text)
    }
}