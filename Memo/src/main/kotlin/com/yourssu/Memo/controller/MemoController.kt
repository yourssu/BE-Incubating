package com.yourssu.Memo.controller

import com.yourssu.Memo.dtos.request.RequestCreateMemoDto
import com.yourssu.Memo.dtos.request.RequestUpdateMemoDto
import com.yourssu.Memo.dtos.response.ResponseCreateMemoDto
import com.yourssu.Memo.dtos.response.ResponseShowByPageMenuDto
import com.yourssu.Memo.dtos.response.ResponseShowMemoDto
import com.yourssu.Memo.dtos.response.ResponseUpdateMemoDto
import com.yourssu.Memo.service.MemoService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*

@Controller
@RequestMapping("/memos")
@ResponseBody
class MemoController {

    private val memoService : MemoService;

    constructor(ms: MemoService){
        this.memoService=ms
    }

    @PostMapping()
    fun saveMemo(@RequestBody requestCreateMemoDto: RequestCreateMemoDto) : ResponseCreateMemoDto{
        return memoService.saveMemo(requestCreateMemoDto);
    }

    @PutMapping("/{memoId}")
    fun updateMemo(@PathVariable("memoId") Id : Long, @RequestBody requestUpdateMemoDto: RequestUpdateMemoDto) : ResponseUpdateMemoDto{
        return memoService.updateMemo(Id, requestUpdateMemoDto)
    }

    @DeleteMapping("/{memoId}")
    fun deleteMemo(@PathVariable(name = "memoId") id : Long){
        memoService.deleteMemo(id)
    }

    @GetMapping("/{memoId}")
    fun showMemoById(@PathVariable("memoId") id : Long) : ResponseShowMemoDto{
        return memoService.showMemoById(id)
    }

    @GetMapping
    fun showMemoByPage(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date :Date, @RequestParam("page") page : Int) : ResponseShowByPageMenuDto{
        println("55555555555555555555555")
        return memoService.showMemoByPage(date, page)
    }
}