package com.incubate.kotlinmemo.controller

import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import com.incubate.kotlinmemo.dto.MemoPreviewDto
import com.incubate.kotlinmemo.dto.MemoResponseDto
import com.incubate.kotlinmemo.service.MemoService
import io.swagger.annotations.ApiOperation
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.lang.IllegalStateException
import java.time.LocalDate

@Controller
@RequestMapping("/memos")
@ResponseBody
class MemoController(private val memoService:MemoService) {

    @PostMapping()
    @ApiOperation("메모 생성")
    fun createMemo(@RequestBody memo:MemoCreateUpdateDto):MemoResponseDto{
        return memoService.createMemo(memo)
    }

    @PutMapping("/{memoId}")
    @ApiOperation("메모 수정")
    fun updateMemo(@PathVariable("memoId") memo_id:Long, @RequestBody memo:MemoCreateUpdateDto):MemoPreviewDto{
        return memoService.updateMemo(memo_id, memo)
    }

    @DeleteMapping("/{memoId}")
    @ApiOperation("메모 삭제")
    fun deleteMemo(@PathVariable("memoId") memo_id:Long){
        this.memoService.deleteMemo(memo_id)
    }

    @GetMapping()
    @ApiOperation("메모 날짜 기준 최근순 조회")
    fun MemoInfoByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date:LocalDate, @RequestParam("page") page:Int):List<MemoPreviewDto> {
        return memoService.MemoInfoByDate(date,page)
    }

    @GetMapping("/{memoId}")
    @ApiOperation("메모 정보 조회")
    fun MemoInfo(@PathVariable("memoId") memo_id: Long):MemoResponseDto{
        return memoService.MemoInfo(memo_id)
    }

}