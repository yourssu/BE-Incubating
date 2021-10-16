package haneul.kotlinspring.controllerTest

import com.fasterxml.jackson.databind.ObjectMapper
import haneul.kotlinspring.model.dto.RequestDto
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import javax.transaction.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CreateMemoTest {

    @Autowired
    private lateinit var mockMve:MockMvc   // lateinit은 var 프로퍼티만 사용 가능

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("메모 생성(성공)")
    fun createMemoSuccess() {
        //given
        val dto = RequestDto(
                title = "created !",
                text = "created"
        )
        //when
        val test = mockMve.post("/memos") {
            contentType = MediaType.APPLICATION_JSON     // json 형식으로 데이터를 보낸다고 명시
            content = objectMapper.writeValueAsString(dto)   // dto를 json형식의 String으로 만들기 위해 objectMapper 사용
            accept = MediaType.APPLICATION_JSON
        }
        //then
        test.andExpect {
            status { isCreated() }
            jsonPath("id") { exists() }
        }
    }

    @Test
    @DisplayName("메모 생성(실패) - title don't exist")
    fun createMemoFailBecauseTitleNull() {
        //given
        val dto = RequestDto(
                title = "",
                text = "created"
        )
        //when
        val test = mockMve.post("/memos") {
            contentType = MediaType.APPLICATION_JSON     // josn 형식으로 데이터를 보낸다고 명시
            content = objectMapper.writeValueAsString(dto)   // dto를 json형식의 String으로 만들기 위해 objectMapper 사용
            accept = MediaType.APPLICATION_JSON
        }
        //then
        test.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    @DisplayName("메모 생성(실패) - text don't exist")
    fun createMemoFailBecauseTextNull() {
        //given
        val dto = RequestDto(
                title = "created !",
                text = ""
        )
        //when
        val test = mockMve.post("/memos") {
            contentType = MediaType.APPLICATION_JSON     // josn 형식으로 데이터를 보낸다고 명시
            content = objectMapper.writeValueAsString(dto)   // dto를 json형식의 String으로 만들기 위해 objectMapper 사용
            accept = MediaType.APPLICATION_JSON
        }
        //then
        test.andExpect {
            status { isBadRequest() }
        }
    }
}