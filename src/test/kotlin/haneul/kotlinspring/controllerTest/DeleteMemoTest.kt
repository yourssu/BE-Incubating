package haneul.kotlinspring.controllerTest

import haneul.kotlinspring.model.entity.Memo
import haneul.kotlinspring.model.repository.MemoRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
class DeleteMemoTest {

    @Autowired
    lateinit var mockMve: MockMvc   // lateinit은 var 프로퍼티만 사용 가능

    @Autowired
    lateinit var memoRepository: MemoRepository

    @Test
    @DisplayName("메모 삭제(성공)")
    fun deleteSuccess() {
        //given
        val memo = Memo(
                title = "created !",
                text = "creeated",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
        )
        memoRepository.save(memo)
        //when
        val test = mockMve.delete("/memos/{memoId}", memo.id)
        //then
        test.andExpect {
            status { isOk() }
        }
    }

    @Test
    @DisplayName("메모 삭제(실패) - id에 해당하는 메모 없음")
    fun deleteFailBecauseNotFoundMemo() {
        //given
        val memo = Memo(
                title = "created !",
                text = "creeated",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
        )
        memoRepository.save(memo)
        //when
        val test = mockMve.delete("/memos/{memoId}", memo.id + 1L)
        //then
        test.andExpect {
            status { isNotFound() }
        }
    }
}