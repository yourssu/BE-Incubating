package com.yourssu_incubating.demo.controller.request

import javax.validation.constraints.NotNull

data class UpdateMemoRequest (
    @field: NotNull
    var title: String,

    @field: NotNull
    var text: String,
)