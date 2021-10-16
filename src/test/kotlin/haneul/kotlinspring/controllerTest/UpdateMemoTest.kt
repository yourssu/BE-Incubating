package haneul.kotlinspring.controllerTest

import com.fasterxml.jackson.databind.ObjectMapper
import haneul.kotlinspring.model.dto.RequestDto
import haneul.kotlinspring.model.entity.Memo
import haneul.kotlinspring.model.repository.MemoRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.put
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.transaction.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UpdateMemoTest {

    @Autowired
    private lateinit var mockMve: MockMvc   // lateinit은 var 프로퍼티만 사용 가능

    @Autowired
    lateinit var memoRepository: MemoRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("메모 수정(성공)")
    fun updateSuccess() {
        //given
        val memo = memoRepository.save(
                Memo(
                        title = "created !",
                        text = "created",
                        createdAt = LocalDateTime.now(),
                        updatedAt = LocalDateTime.now()
                )
        )
        val dto = RequestDto(
                title = "updated !",
                text = "updated"
        )
        val updatedAt = memo.updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        //when
        val test = mockMve.put("/memos/{memoId}", memo.id) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(dto)
            accept = MediaType.APPLICATION_JSON
        }
        //then
        test.andExpect {
            status { isOk() }
            jsonPath("id") { exists() }
            jsonPath("title") { value("updated !")}
            jsonPath("updatedAt") { value(updatedAt)}
        }
    }

    @Test
    @DisplayName("메모 수정(실패) - 해당 id 없음")
    fun updateFailBecauseNotFound() {
        //given
        val memo = memoRepository.save(
                Memo(
                        title = "created !",
                        text = "created",
                        createdAt = LocalDateTime.now(),
                        updatedAt = LocalDateTime.now()
                )
        )
        val dto = RequestDto(
                title = "updated !",
                text = "updated"
        )
        val updatedAt = memo.updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        //when
        val test = mockMve.put("/memos/{memoId}", memo.id + 1L) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(dto)
            accept = MediaType.APPLICATION_JSON
        }
        //then
        test.andExpect {
            status { isNotFound() }
        }
    }

    @Test
    @DisplayName("메모 수정(실패) - 메모 title/text null")
    fun updateFailBecauseDtoNull() {
        //given
        val memo = memoRepository.save(
                Memo(
                        title = "created !",
                        text = "created",
                        createdAt = LocalDateTime.now(),
                        updatedAt = LocalDateTime.now()
                )
        )
        val dto = RequestDto(
                title = null,
                text = null
        )
        val updatedAt = memo.updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        //when
        val test = mockMve.put("/memos/{memoId}", memo.id) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(dto)
            accept = MediaType.APPLICATION_JSON
        }
        //then
        test.andExpect {
            status { isBadRequest() }
        }
    }
}