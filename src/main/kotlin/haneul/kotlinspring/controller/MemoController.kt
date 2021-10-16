package haneul.kotlinspring.controller

import haneul.kotlinspring.model.dto.DetailResponseDto
import haneul.kotlinspring.model.dto.PageResponseDto
import haneul.kotlinspring.model.dto.RequestDto
import haneul.kotlinspring.model.dto.SimpleResponseDto
import haneul.kotlinspring.service.MemoService
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/memos")
class MemoController (private val memoService: MemoService){

    @ApiOperation("메모 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody requestDto:RequestDto): DetailResponseDto {
        return memoService.createMemo(requestDto)
    }

    @ApiOperation("메모 수정")
    @PutMapping("/{memoId}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable("memoId") id:Long,
               @RequestBody requestDto:RequestDto
    ): SimpleResponseDto {
        return memoService.updateMemo(id, requestDto)
    }

    @ApiOperation("메모 삭제")
    @DeleteMapping("/{memoId}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable("memoId") id:Long) {
        memoService.deleteMemo(id)
    }

    @ApiOperation("메모 상세조회")
    @GetMapping("/{memoId}")
    @ResponseStatus(HttpStatus.OK)
    fun detail(@PathVariable("memoId") id:Long): DetailResponseDto {
        return memoService.detailMemo(id)
    }

    @ApiOperation("날짜별 메모 검색")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun search(@RequestParam("date")
               @DateTimeFormat(pattern = "yyyy-MM-dd") date:LocalDate,
               @RequestParam("page") page:Int?
    ): PageResponseDto {
        val page = page ?: 0
        return memoService.findMemo(date, page)
    }

    @ApiOperation("메모 전체조회")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun all(@RequestParam("page") page:Int?, @RequestParam("size") size:Int?
    ): PageResponseDto{
        val page = page ?: 0
        val size = size ?: 5
        return memoService.allMemo(page, size)
    }


}