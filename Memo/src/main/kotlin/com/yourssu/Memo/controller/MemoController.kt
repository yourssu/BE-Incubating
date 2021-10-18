package com.yourssu.Memo.controller

import com.yourssu.Memo.dtos.request.RequestCreateMemoDto
import com.yourssu.Memo.dtos.request.RequestUpdateMemoDto
import com.yourssu.Memo.dtos.response.ResponseCreateMemoDto
import com.yourssu.Memo.dtos.response.ResponseShowByPageMenuDto
import com.yourssu.Memo.dtos.response.ResponseShowMemoDto
import com.yourssu.Memo.dtos.response.ResponseUpdateMemoDto
import com.yourssu.Memo.service.MemoService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
@RequestMapping("/memos")
@ResponseBody
class MemoController {

    private val memoService : MemoService;

    constructor(ms: MemoService){
        this.memoService=ms
    }

    @ApiOperation("새로운 메모 생성")
    @PostMapping()
    fun saveMemo(@RequestBody requestCreateMemoDto: RequestCreateMemoDto) : ResponseCreateMemoDto{
        return memoService.saveMemo(requestCreateMemoDto);
    }

    @ApiOperation(value = "메모 수정", notes = "메모의 제목, 내용 수정")
    @ApiImplicitParam(name = "memoId", dataType = "long", value = "데이터베이스 내의 메모의 pk")
    @PutMapping("/{memoId}")
    fun updateMemo(@PathVariable("memoId") Id : Long, @RequestBody requestUpdateMemoDto: RequestUpdateMemoDto) : ResponseUpdateMemoDto{
        return memoService.updateMemo(Id, requestUpdateMemoDto)
    }

    @ApiOperation("메모 삭제")
    @ApiImplicitParam(name = "memoId", dataType = "long", value = "데이터베이스 내의 메모의 pk")
    @DeleteMapping("/{memoId}")
    fun deleteMemo(@PathVariable(name = "memoId") id : Long){
        memoService.deleteMemo(id)
    }

    @ApiOperation("메모 1개 조회")
    @ApiImplicitParam(name = "memoId", dataType = "long", value = "데이터베이스 내의 메모의 pk")
    @GetMapping("/{memoId}")
    fun showMemoById(@PathVariable("memoId") id : Long) : ResponseShowMemoDto{
        return memoService.showMemoById(id)
    }

    @ApiOperation(value = "메모 5개 반환", notes = "날짜에 따른 메모 5개 조회, 최근에 작성된 메모를 먼저 반환한다")
    @GetMapping
    fun showMemoByPage(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date :Date, @RequestParam("page") page : Int) : ResponseShowByPageMenuDto{
        return memoService.showMemoByPage(date, page)
    }
}