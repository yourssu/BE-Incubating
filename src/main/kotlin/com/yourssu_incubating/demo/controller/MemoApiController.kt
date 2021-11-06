package com.yourssu_incubating.demo.controller

import com.yourssu_incubating.demo.controller.request.SaveMemoRequest
import com.yourssu_incubating.demo.controller.request.UpdateMemoRequest
import com.yourssu_incubating.demo.controller.response.MemoResponse
import com.yourssu_incubating.demo.controller.response.SearchByDateMemosResponse
import com.yourssu_incubating.demo.entity.memo.Memos
import com.yourssu_incubating.demo.service.MemoService
import com.yourssu_incubating.demo.utils.FormatUtil.localDateTimeToString
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import javax.validation.Valid

@RestController
@RequestMapping("/memos")
class MemoApiController(private val memoService: MemoService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveMemo(@Valid @RequestBody request: SaveMemoRequest): MemoResponse {
        val memo = memoService.saveMemo(request)
        return MemoResponse(
            memo.id,
            memo.title,
            memo.text,
            localDateTimeToString(memo.createdAt),
            localDateTimeToString(memo.updatedAt)
        )
    }

    @PutMapping("/{memoId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateMemo(
        @PathVariable memoId: Long,
        @Valid @RequestBody request: UpdateMemoRequest
    ): MemoResponse {
        val memo: Memos = memoService.updateMemo(memoId, request)
        return MemoResponse(
            memo.id,
            memo.title,
            memo.text,
            localDateTimeToString(memo.createdAt),
            localDateTimeToString(memo.updatedAt)
        )
    }

    @GetMapping("/{memoId}")
    @ResponseStatus(HttpStatus.OK)
    fun getMemoWithMemoId(@PathVariable memoId: Long): MemoResponse {
        val memo: Memos = memoService.getMemo(memoId)
        return MemoResponse(
            memo.id,
            memo.title,
            memo.text,
            localDateTimeToString(memo.createdAt),
            localDateTimeToString(memo.updatedAt)
        )
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun searchMemoWithDate(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        @PageableDefault(size = 5) pageable: Pageable
    ): SearchByDateMemosResponse {
        return SearchByDateMemosResponse(memoService.searchByDate(date, pageable))
    }

    @DeleteMapping("/{memoId}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteMemoWithMemoId(@PathVariable memoId: Long) {
        memoService.deleteMemo(memoId)
    }
}