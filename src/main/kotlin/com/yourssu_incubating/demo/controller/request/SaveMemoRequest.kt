package com.yourssu_incubating.demo.controller.request

import javax.validation.constraints.NotNull

class SaveMemoRequest (
    @field: NotNull(message = "title is must be not null")
    val title: String,

    @field: NotNull(message = "text is must be not null")
    val text: String,
)