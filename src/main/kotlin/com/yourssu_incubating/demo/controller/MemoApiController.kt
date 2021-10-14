package com.yourssu_incubating.demo.controller

import com.yourssu_incubating.demo.controller.request.SaveMemoRequest
import com.yourssu_incubating.demo.controller.request.UpdateMemoRequest
import com.yourssu_incubating.demo.controller.response.MemoResponse
import com.yourssu_incubating.demo.controller.response.SearchByDateMemosResponse
import com.yourssu_incubating.demo.entity.memo.Memos
import com.yourssu_incubating.demo.service.MemoService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/memos")
class MemoApiController {

    @Autowired
    private lateinit var memoService: MemoService

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveMemo(@Valid @RequestBody requestDto: SaveMemoRequest): MemoResponse {
        val memo = memoService.saveMemo(requestDto)
        return MemoResponse(memo.id, memo.title, memo.text, memo.getStringCreatedAt(), memo.getStringUpdatedAt())
    }

    @PutMapping("/{memoId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateMemo(@PathVariable memoId: Long, @Valid @RequestBody requestDto: UpdateMemoRequest): MemoResponse {
        val memo: Memos = memoService.updateMemo(memoId, requestDto)
        return MemoResponse(memo.id, memo.title, memo.text, memo.getStringCreatedAt(), memo.getStringUpdatedAt())
    }

    @GetMapping("/{memoId}")
    @ResponseStatus(HttpStatus.OK)
    fun getMemoWithMemoId(@PathVariable memoId: Long): MemoResponse {
        val memo: Memos = memoService.getMemo(memoId)
        return MemoResponse(memo.id, memo.title, memo.text, memo.getStringCreatedAt(), memo.getStringUpdatedAt())
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun searchMemoWithDate(@RequestParam param: Map<String, String>, @PageableDefault(size = 5) pageable: Pageable): SearchByDateMemosResponse {
        return SearchByDateMemosResponse(memoService.searchByDate(param["date"]!!, pageable))
    }

    @DeleteMapping("/{memoId}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteMemoWithMemoId(@PathVariable memoId: Long) {
        memoService.deleteMemo(memoId)
    }
}