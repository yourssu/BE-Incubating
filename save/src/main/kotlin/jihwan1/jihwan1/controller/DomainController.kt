package jihwan1.jihwan1.controller

import io.swagger.annotations.ApiOperation
import javassist.NotFoundException
import jihwan1.jihwan1.dto.Detail
import jihwan1.jihwan1.dto.Request
import jihwan1.jihwan1.dto.Simple
import jihwan1.jihwan1.service.MemoService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/memos")
class DomainController(private val memoService: MemoService) {

    @ApiOperation(value = "메모 생성")
    @PostMapping
    fun post(@RequestBody request: Request, response: HttpServletResponse) : Detail{
        try {
            response.status = 201
            return memoService.create(request)
        }catch (e : NoSuchFieldException){
            response.status = 400
            return Detail(null,null,null,null,null)
        }
    }

    @ApiOperation(value = "메모 수정")
    @PutMapping("/{memoid}")
    fun put(@PathVariable("memoid") id: Long,@RequestBody request: Request, response: HttpServletResponse) : Simple{
        try {
            response.status = 200
            return memoService.update(id, request);
        }catch(e : NotFoundException){
            response.status = 404;
            return Simple(null,null,null,null)
        }
    }

    @ApiOperation("메모 삭제")
    @DeleteMapping("/{memoId}")
    fun delete(@PathVariable("memoId") id:Long,response: HttpServletResponse){
        try {
            memoService.delete(id)
        }catch (e : NotFoundException){
            response.status = 404
        }
    }

    @ApiOperation("메모상세조회")
    @GetMapping("{memoId}")
    fun detailGet(@PathVariable("memoId") id:Long,response: HttpServletResponse) : Detail{
        try {
            return memoService.getDetail(id)
        }catch (e : NotFoundException){
            response.status = 404
            return Detail(null,null,null,null,null)
        }
    }

}