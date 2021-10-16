package haneul.kotlinspring.serviceTest

import haneul.kotlinspring.exception.MemoNotFoundException
import haneul.kotlinspring.model.entity.Memo
import haneul.kotlinspring.model.repository.MemoRepository
import haneul.kotlinspring.service.MemoService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class FindMemoTest {
    @Mock
    lateinit var memoRepository: MemoRepository

    @InjectMocks
    lateinit var memoService: MemoService

    private fun <T> any(): T = Mockito.any<T>()

    @Test
    @DisplayName("메모 상세정보(성공)")
    fun findMemoSuccess() {
        //given
        given(memoRepository.findById(1)).willReturn(
            Optional.of(
                Memo(
                    id = 1,
                    title = "test",
                    text = "test",
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now()
                )
            )
        )
        //when
        val res = memoService.detailMemo(1)
        //then
        assertEquals(1, res.id)
        assertEquals("test", res.title)
        assertEquals("test", res.text)
    }

    @Test
    @DisplayName("메모 상세조회(실패) - 해당 id 메모 없음")
    fun findMemoFailBecauseNotFound() {
        //given
        given(memoRepository.findById(1)).willReturn(Optional.empty())
        //when
        //then
        assertThrows<MemoNotFoundException> {
            memoService.deleteMemo(1)
        }
    }

    @Test
    @DisplayName("메모 날짜검색(성공)")
    fun findByDateMemoSuccess() {
        //given
        val memos = generateMemos()
        val date = LocalDate.now()
        given(memoRepository.findAllByCreatedAtBetween(any(), any(), any()))
                .willReturn(memos)
        //when
        val res = memoService.findMemo(date, 0)
        //then
        assertEquals(10, res.memos.size)
        assertEquals(10, res.totalElements)
    }

    @Test
    @DisplayName("메모 날짜검색(실패) - 해당 날짜에 메모 없음")
    fun findByDateMemoFailBecauseNotFound() {
        //given
        val date = LocalDate.of(2000,12,1)
        given(memoRepository.findAllByCreatedAtBetween(any(), any(), any()))
                .willReturn(Page.empty())
        //when
        //then
        assertThrows<MemoNotFoundException> {
            memoService.findMemo(date, 0)
        }
    }

    @Test
    @DisplayName("메모 전체조회(성공)")
    fun findAllMemoSuccess() {
        //given
        val memos = generateMemos()
        given(memoRepository.findAll(pageable = any()))
                .willReturn(memos)
        //when
        val res = memoService.allMemo(0, 5)
        //then
        assertEquals(10, res.memos.size)
        assertEquals(10, res.totalElements)
    }

    @Test
    @DisplayName("메모 전체조회(실패) - 메모 1개도 없음")
    fun findAllMemoFailBecauseNotFound() {
        //given
        given(memoRepository.findAll(pageable = any()))
                .willReturn(Page.empty())
        //when
        //then
        assertThrows<MemoNotFoundException> {
            memoService.allMemo(0, 5)
        }
    }

    fun generateMemos(): PageImpl<Memo> {
        val memos = mutableListOf<Memo>().apply {
            for (i in 0..9) {
                add(Memo(
                        id = i.toLong(),
                        title = "memo${i}",
                        text = "test",
                        createdAt = LocalDateTime.now(),
                        updatedAt = LocalDateTime.now()
                ))
            }
        }
        return PageImpl<Memo>(memos)
    }
}