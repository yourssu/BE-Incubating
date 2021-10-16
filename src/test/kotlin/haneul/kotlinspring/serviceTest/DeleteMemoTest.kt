package haneul.kotlinspring.serviceTest

import haneul.kotlinspring.exception.MemoNotFoundException
import haneul.kotlinspring.model.entity.Memo
import haneul.kotlinspring.model.repository.MemoRepository
import haneul.kotlinspring.service.MemoService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class DeleteMemoTest {

    @Mock
    lateinit var memoRepository: MemoRepository

    @InjectMocks
    lateinit var memoService: MemoService

    @Test
    @DisplayName("메모 삭제(성공)")
    fun deleteMemoSuccess() {
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
        memoService.deleteMemo(1)
    }

    @Test
    @DisplayName("메모 삭제(실패) - 해당 id 메모 없음")
    fun deleteMemoFailBecauseNotFound() {
        //given
        given(memoRepository.findById(1)).willReturn(Optional.empty())
        //when
        //then
        assertThrows<MemoNotFoundException> {
            memoService.deleteMemo(1)
        }
    }
}