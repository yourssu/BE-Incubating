package com.incubate.kotlinmemo.unit

import com.incubate.kotlinmemo.domain.Memo
import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import com.incubate.kotlinmemo.dto.MemoPreviewDto
import com.incubate.kotlinmemo.dto.MemoResponseDto
import com.incubate.kotlinmemo.repository.MemoRepository
import com.incubate.kotlinmemo.service.MemoServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.BDDMockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyByte
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.data.domain.Page
import org.springframework.data.domain.Page.empty
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import javax.persistence.EntityManager
import kotlin.collections.ArrayList


@RunWith(MockitoJUnitRunner::class)
class MemoServiceTest {
    @Mock
    lateinit var em:EntityManager
    @Mock
    lateinit var memoRepository: MemoRepository
    @InjectMocks
    lateinit var memoService:MemoServiceImpl

    @Test
    fun createMemo(){

        val memoDto:MemoCreateUpdateDto = MemoCreateUpdateDto("memo_title", "memo_text")

        var memo:Memo = Memo(id = 1L, title = "memo_title", text = "memo_text")

        `when`(memoRepository.findById(memo.id)).thenReturn(Optional.of(memo))
        `when`(memoRepository.save(org.mockito.kotlin.any())).thenReturn(memo)

        val responseMemo : MemoResponseDto = memoService.createMemo(memoDto)
        val findByIdMemo = memoRepository.findById(1L)

        Assert.assertEquals(responseMemo.title, findByIdMemo.get().title)
        Assert.assertEquals(responseMemo.text, findByIdMemo.get().text)
        Assert.assertEquals(responseMemo.createdAt, findByIdMemo.get().createdAt)

    }


    @Test
    fun MemoInfo(){
        var memo1:Memo = Memo(id= 2L, title = "memo_title", text = "memo_text")

        var memo2:Memo = Memo(id = 3L, title = "memo_title", text = "memo_text")


        `when`(memoRepository.findById(memo1.id)).thenReturn(Optional.of(memo1))
        `when`(memoRepository.findById(memo2.id)).thenReturn(Optional.of(memo2))

        val responseMemo:MemoResponseDto = memoService.MemoInfo(memo1.id)
        val findByIdMemo:Memo = memoRepository.findById(memo1.id).get()

        Assert.assertEquals(responseMemo.title,findByIdMemo.title)
        Assert.assertEquals(responseMemo.text, findByIdMemo.text)
        Assert.assertEquals(responseMemo.createdAt,findByIdMemo.createdAt)

    }

    @Test
    fun delete(){
        var memo:Memo = Memo(id = 4L, title = "memo_title", text = "memo_text")
        memoRepository.save(memo)

        memoRepository.delete(memo)

       verify(memoRepository).delete(any(Memo::class.java))
    }

    @Test
    fun updateMemo(){
        var memo:Memo = Memo(id = 5L, title = "memo_title", text = "memo_text")

        val updateDto: MemoCreateUpdateDto = MemoCreateUpdateDto("update_title","update_text")

        var updatedMemo:Memo = Memo(id = 5L, title = "update_title", text = "update_text", updatedAt = LocalDateTime.now())

        `when`(memoRepository.findById(memo.id)).thenReturn(Optional.of(updatedMemo))

        val result : MemoPreviewDto = memoService.updateMemo(5L,updateDto)
        val findByIdMemo: Memo = memoRepository.findById(5L).get()

        Assert.assertEquals(result.title,findByIdMemo.title)
        Assert.assertEquals(result.createdAt,findByIdMemo.createdAt)
        Assert.assertEquals(result.updatedAt,findByIdMemo.updatedAt)
    }

    @Test
    fun getMemoAfterCreatedAtByPaging(){
        var memoList:ArrayList<Memo> = ArrayList()

        val memo1 = Memo(id = 6L, title = "memo1_title", text = "memo1_text")
        memoList.add(memo1)

        val memo2 = Memo(id = 7L, title = "memo2_title", text = "memo2_text")
        memoList.add(memo2)

        val memo3 = Memo(id = 8L, title = "memo3_title", text = "memo3_text")
        memoList.add(memo3)

        val pageRequest:PageRequest = PageRequest.of(1,5)
        val memoPage:Page<Memo> = PageImpl(memoList.subList(0,memoList.size),pageRequest,memoList.size.toLong())
        `when`(memoRepository.getMemoAfterCreatedAtByPaging(LocalDateTime.of(2020,1,1,0,0),pageRequest)).thenReturn(memoPage)


        var resultList :List<MemoPreviewDto> = memoService.getMemoAfterCreatedAtByPaging(LocalDate.of(2020,1,1),1)
        var repositoryList: Page<Memo> = memoRepository.getMemoAfterCreatedAtByPaging(LocalDateTime.of(2020,1,1,0,0),pageRequest)
        assertThat(resultList.size).isEqualTo(repositoryList.content.size)
        Assert.assertSame(repositoryList.content.get(0),memoList.get(0))
        Assert.assertSame(repositoryList.content.get(1),memoList.get(1))
        Assert.assertSame(repositoryList.content.get(2),memoList.get(2))

    }
}