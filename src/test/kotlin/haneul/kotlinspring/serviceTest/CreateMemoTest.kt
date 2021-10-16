package haneul.kotlinspring.serviceTest

import haneul.kotlinspring.model.dto.RequestDto
import haneul.kotlinspring.model.entity.Memo
import haneul.kotlinspring.model.repository.MemoRepository
import haneul.kotlinspring.service.MemoService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class CreateMemoTest {

    @Mock
    lateinit var memoRepository: MemoRepository

    @InjectMocks
    lateinit var memoService: MemoService

    @Test
    @DisplayName("메모 생성(성공)")
    fun createMemoSuccess() {
        //given
        given(memoRepository.save(any())).willReturn(
                Memo(
                        id = 1L,
                        title = "testTitle",
                        text = "testText",
                        createdAt = LocalDateTime.now(),
                        updatedAt = LocalDateTime.now()
                )
        )
        val dto = RequestDto(
                title = "testTitle",
                text = "testText"
        )
        //when
        val res = memoService.createMemo(dto)
        //then
        assertEquals(1L, res.id)
        assertEquals("testTitle", res.title)
        assertEquals("testText", res.text)
    }
}