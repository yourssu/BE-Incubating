package jihwan1.jihwan1.service

import javassist.NotFoundException
import jihwan1.jihwan1.domain.Memo
import jihwan1.jihwan1.dto.Request
import jihwan1.jihwan1.repository.MemoRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemoServiceIntegrationTest {

    @Autowired lateinit var memoService: MemoService
    @Autowired lateinit var memoRepository: MemoRepository

    @Test
    fun create(){
        //given
        var request = Request(title = "test", text = "test")


        //when
        var save = memoService.create(request)

        //then
        val get = memoRepository.findById(save.id as Long).get()
        assertThat(save.title).isEqualTo(get.title)
        assertThat(save.text).isEqualTo(get.text)

    }


    @Test
    fun update(){
        //given
        var save = memoService.create(Request(title = "before", text = "before"))
        var request = Request(title = "After", text = "After")

        //when
        var update = memoService.update(save.id as Long, request)

        //then
        val get = memoRepository.findById(save.id as Long).get()
        assertThat(update.title).isEqualTo(get.title)
        assertThat("After").isEqualTo(get.text)
    }

    @Test
    fun delete(){
        var save = memoService.create(Request(title = "test", text = "test"))

        //when
        memoService.delete(save.id as Long)



    }

    @Test
    fun getDetail(){
        var save = memoService.create(Request(title = "test", text = "test"))

        //when
        var get = memoService.getDetail(save.id as Long)

        //then
        assertThat(get.text).isEqualTo(save.text)
        assertThat(get.title).isEqualTo(save.title)

    }


}