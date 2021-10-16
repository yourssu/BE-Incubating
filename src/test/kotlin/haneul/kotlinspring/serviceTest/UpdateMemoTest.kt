package haneul.kotlinspring.serviceTest

import haneul.kotlinspring.exception.MemoNotFoundException
import haneul.kotlinspring.model.dto.RequestDto
import haneul.kotlinspring.model.entity.Memo
import haneul.kotlinspring.model.repository.MemoRepository
import haneul.kotlinspring.service.MemoService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class UpdateMemoTest {
    @Mock
    lateinit var memoRepository: MemoRepository

    @InjectMocks
    lateinit var memoService: MemoService

    @Test
    @DisplayName("메모 수정(성공)")
    fun updateMemoSuccess() {
        //given
        val memo = Memo(
                    id = 1L,
                    title = "test",
                    text = "test",
                    createdAt = LocalDate.now().atStartOfDay(),
                    updatedAt = LocalDate.now().atStartOfDay()
                )
        given(memoRepository.save(Mockito.any())).willReturn(memo)
        given(memoRepository.findById(1)).willReturn(Optional.of(memo))
        val dto = RequestDto(
                title = "update!",
                text = "update다!!"
        )
        //when
        val res = memoService.updateMemo(1, dto)
        //then
        assertEquals("update!", res.title)
        assertNotEquals(LocalDate.now().atStartOfDay(), res.updatedAt)
    }

    @Test
    @DisplayName("메모 수정(실패) - id에 해당하는 메모 없음")
    fun updateMemoFailBecauseNotFound() {
        //given
        given(memoRepository.findById(1)).willReturn(Optional.empty())
        val dto = RequestDto(
                title = "update!",
                text = "update다!!"
        )
        //when
        //then
        assertThrows<MemoNotFoundException> {
            memoService.updateMemo(1, dto)
        }
    }
}