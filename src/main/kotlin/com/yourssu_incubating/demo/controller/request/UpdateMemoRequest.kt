package com.yourssu_incubating.demo.controller.request

import javax.validation.constraints.NotNull

data class UpdateMemoRequest (
    @field: NotNull
    val title: String,

    @field: NotNull
    val text: String,
)