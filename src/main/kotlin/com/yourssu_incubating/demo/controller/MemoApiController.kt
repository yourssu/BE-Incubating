package com.yourssu_incubating.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/memo")
class MemoApiController {

    @PostMapping("/")
    fun saveMeno() {}

    @PostMapping("/{memoId}")
    fun updateMeno() {}

    @GetMapping("/{memoId}")
    fun getMemo() {}

    @GetMapping("/")
    fun searchMemoWithDate() {}

    @DeleteMapping("/{memoId}")
    fun deleteMemo() {}


}