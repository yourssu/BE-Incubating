package haneul.kotlinspring.service

import haneul.kotlinspring.exception.MemoBadRequestException
import haneul.kotlinspring.exception.MemoNotFoundException
import haneul.kotlinspring.model.dto.DetailResponseDto
import haneul.kotlinspring.model.dto.PageResponseDto
import haneul.kotlinspring.model.dto.RequestDto
import haneul.kotlinspring.model.dto.SimpleResponseDto
import haneul.kotlinspring.model.entity.Memo
import haneul.kotlinspring.model.repository.MemoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class MemoService (private val memoRepository: MemoRepository){

    fun createMemo(dto: RequestDto): DetailResponseDto {
        if(dto.title.isNullOrEmpty() || dto.text.isNullOrEmpty())
            throw MemoBadRequestException()
        val memo = memoRepository.save(
                Memo(
                        title = dto.title,
                        text = dto.text,
                        createdAt = LocalDateTime.now(),
                        updatedAt = LocalDateTime.now()
                )
        )
        return DetailResponseDto(memo.id, memo.title, memo.text, memo.createdAt, memo.updatedAt)
    }

    fun updateMemo(id:Long, dto: RequestDto) : SimpleResponseDto {
        if(dto.title.isNullOrEmpty() && dto.text.isNullOrEmpty())
            throw MemoBadRequestException()
        val memo = memoRepository.findByIdOrNull(id)?: let{ throw MemoNotFoundException() }
        memo.title = dto.title.toString()
        memo.text = dto.text.toString()
        memo.updatedAt = LocalDateTime.now()
        val res = memoRepository.save(memo)

        return SimpleResponseDto(res.id, res.title, res.createdAt, res.updatedAt)
    }

    fun deleteMemo(id:Long) {
        val memo = memoRepository.findByIdOrNull(id)?: let{ throw MemoNotFoundException() }
        memoRepository.delete(memo)
    }

    fun detailMemo(id:Long): DetailResponseDto{
        val memo = memoRepository.findByIdOrNull(id)?: let{ throw MemoNotFoundException() }
        return DetailResponseDto(memo.id, memo.title, memo.text, memo.createdAt, memo.updatedAt)
    }

    fun findMemo(date: LocalDate, page:Int) : PageResponseDto {
        val start: LocalDateTime = date.atStartOfDay()
        val end: LocalDateTime = LocalDateTime.of(date, LocalTime.of(23, 59, 59))
        val pageRequest: PageRequest = PageRequest.of(page, 5, Sort.by("createdAt").descending())

        val memoPage: Page<Memo> = memoRepository.findAllByCreatedAtBetween(start, end, pageRequest)
        if(memoPage.isEmpty) throw MemoNotFoundException()

        val memoList = mutableListOf<SimpleResponseDto>().apply {
                for (m in memoPage) {
                    add(SimpleResponseDto(m.id, m.title, m.createdAt, m.updatedAt))
                }
        }
        return PageResponseDto(memoList, memoPage)
    }

    fun allMemo(page: Int, size: Int): PageResponseDto {
        val pageRequest: PageRequest = PageRequest.of(page, size)
        val memoPage: Page<Memo> = memoRepository.findAll(pageRequest)
        if(memoPage.isEmpty) throw MemoNotFoundException()

        val memoList = mutableListOf<SimpleResponseDto>().apply {
            for (m in memoPage) {
                add(SimpleResponseDto(m.id, m.title, m.createdAt, m.updatedAt))
            }
        }
        return PageResponseDto(memoList, memoPage)
    }
}