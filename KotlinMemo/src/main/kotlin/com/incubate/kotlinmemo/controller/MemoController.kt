package com.incubate.kotlinmemo.controller

import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import com.incubate.kotlinmemo.dto.MemoPreviewDto
import com.incubate.kotlinmemo.dto.MemoResponseDto
import com.incubate.kotlinmemo.service.MemoService
import lombok.RequiredArgsConstructor
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.lang.IllegalStateException
import java.time.LocalDate

@Controller
@RequestMapping("/memos")
@ResponseBody
class MemoController(private val memoService:MemoService) {

    @PostMapping()
    fun createMemo(@RequestBody memo:MemoCreateUpdateDto):MemoResponseDto{
        memo.nullCheck()
        return memoService.createMemo(memo)
    }

    @PutMapping("/{memoId}")
    fun updateMemo(@PathVariable("memoId") memo_id:Long, @RequestBody memo:MemoCreateUpdateDto):MemoPreviewDto{
        memo.nullCheck()
        return memoService.updateMemo(memo_id, memo)
    }

    @DeleteMapping("/{memoId}")
    fun deleteMemo(@PathVariable("memoId") memo_id:Long){
        this.memoService.deleteMemo(memo_id)
    }

    @GetMapping()
    fun MemoInfoByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date:LocalDate, @RequestParam("page") page:Int):List<MemoPreviewDto> {
        return memoService.MemoInfoByDate(date,page)
    }

    @GetMapping("/{memoId}")
    fun MemoInfo(@PathVariable("memoId") memo_id: Long):MemoResponseDto{
        return memoService.MemoInfo(memo_id)
    }

}