package haneul.kotlinspring.controllerTest

import haneul.kotlinspring.model.entity.Memo
import haneul.kotlinspring.model.repository.MemoRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.transaction.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class FindMemoTest {

    @Autowired
    lateinit var mockMve: MockMvc   // lateinit은 var 프로퍼티만 사용 가능

    @Autowired
    lateinit var memoRepository: MemoRepository

    @Test
    @DisplayName("메모 상세조회(성공)")
    fun findDetailSuccess() {
        //given
        val memo = Memo(
                title = "created !",
                text = "created",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
        )
        memoRepository.save(memo)
        //when
        val test = mockMve.get("/memos/{memoId}", memo.id)
        //then
        test.andExpect {
            status { isOk() }
            jsonPath("id") { value(memo.id)}
            jsonPath("title") { value("created !") }
            jsonPath("text") { value("created") }
        }
    }

    @Test
    @DisplayName("메모 상세조회(실패) - id에 해당하는 메모 없음")
    fun findDetailFailBecauseNotFound() {
        //given
        val memo = Memo(
                title = "created !",
                text = "created",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
        )
        memoRepository.save(memo)
        //when
        val test = mockMve.get("/memos/{memoId}", memo.id + 1L)
        //then
        test.andExpect {
            status { isNotFound() }
        }
    }

    @Test
    @DisplayName("메모 날짜검색(성공)")
    fun findByDateSuccess() {
        //given
        generateMemoList()
        //when
        val test = mockMve.get("/memos") {
            param("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        }
        //then
        test.andExpect {
            status { isOk() }
            jsonPath("size") { value(5) }
            jsonPath("totalElements") { value(10)}
            jsonPath("totalPages") { value(2) }
        }
    }

    @Test
    @DisplayName("메모 날짜검색(실패) - 해당 날짜에 메모 없음")
    fun findByDateMemoFailBecauseNotFound() {
        //given
        generateMemoList()
        //when
        val test = mockMve.get("/memos") {
            param("date", LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        }
        //then
        test.andExpect {
            status { isNotFound() }
        }
    }

    @Test
    @DisplayName("메모 전체조회(성공)")
    fun findAllSuccess() {
        //given
        generateMemoList()
        //when
        val test = mockMve.get("/memos/all") {
            param("page", "1")
            param("size", "10")
        }
        //then
        test.andExpect {
            status { isOk() }
            jsonPath("size") { value(10) }
            jsonPath("totalElements") { value(30) }
            jsonPath("totalPages") { value(3) }
        }
    }

    @Test
    @DisplayName("메모 전체조회(성공) - 파라미터 없는 경우")
    fun findAllSuccessParameterNull() {
        //given
        generateMemoList()
        //when
        val test = mockMve.get("/memos/all")
        //then
        test.andExpect {
            status { isOk() }
            jsonPath("size") { value(5) }
            jsonPath("totalElements") { value(30) }
            jsonPath("totalPages") { value(6) }
        }
    }

    @Test
    @DisplayName("메모 전체조회(실패) - 메모 1개도 없음")
    fun findAllFailBecauseNotExist() {
        //given X
        //when
        val test = mockMve.get("/memos/all")
        //then
        test.andExpect {
            status { isNotFound() }
        }
    }

    fun generateMemoList() {
        for(i in 0..29) {
            when (i/10) {
                0 -> {
                    memoRepository.save(Memo(
                        title = "created ${i}",
                        text = "created",
                        createdAt = LocalDateTime.now(),
                        updatedAt = LocalDateTime.now()
                    ))
                }
                1 -> {
                    memoRepository.save(Memo(
                        title = "created ${i}!",
                        text = "created",
                        createdAt = LocalDateTime.now().plusMonths(1),
                        updatedAt = LocalDateTime.now().plusMonths(1)
                    ))
                }
                2 -> {
                    memoRepository.save(Memo(
                        title = "created ${i}!",
                        text = "created",
                        createdAt = LocalDateTime.now().plusMonths(2),
                        updatedAt = LocalDateTime.now().plusMonths(2),
                    ))
                }
            }
        }
    }
}