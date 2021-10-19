package com.incubate.kotlinmemo.unit

import com.incubate.kotlinmemo.domain.Memo
import com.incubate.kotlinmemo.dto.MemoCreateUpdateDto
import com.incubate.kotlinmemo.dto.MemoPreviewDto
import com.incubate.kotlinmemo.dto.MemoResponseDto
import com.incubate.kotlinmemo.repository.MemoMemoryRepository
import com.incubate.kotlinmemo.service.MemoServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.BDDMockito.*
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.EntityManager


@RunWith(MockitoJUnitRunner::class)
class MemoServiceTest {

    @Mock
    lateinit var em:EntityManager
    @Mock
    lateinit var memoRepository: MemoMemoryRepository
    @InjectMocks
    lateinit var memoService:MemoServiceImpl

    private fun Dateformatting(localDateTime : LocalDateTime):String{
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDate: String = localDateTime.format(formatter)
        return localDate
    }

    @Test
    fun createMemo(){

        val memoDto:MemoCreateUpdateDto = MemoCreateUpdateDto("memo_title", "memo_text")

        var memo:Memo = Memo(id = 1L, title = "memo_title", text = "memo_text")

        `when`(memoRepository.findById(memo.id)).thenReturn(memo)
        `when`(memoRepository.save(org.mockito.kotlin.any())).thenReturn(memo)

        val responseMemo : MemoResponseDto = memoService.createMemo(memoDto)
        val findByIdMemo = memoRepository.findById(1L)

        Assert.assertEquals(responseMemo.title, findByIdMemo.title)
        Assert.assertEquals(responseMemo.text, findByIdMemo.text)
        Assert.assertEquals(responseMemo.createdAt, Dateformatting(findByIdMemo.createdAt))

    }


    @Test
    fun MemoInfo(){
        var memo1:Memo = Memo(id= 2L, title = "memo_title", text = "memo_text")

        var memo2:Memo = Memo(id = 3L, title = "memo_title", text = "memo_text")


        `when`(memoRepository.findById(memo1.id)).thenReturn(memo1)
        `when`(memoRepository.findById(memo2.id)).thenReturn(memo2)

        val responseMemo:MemoResponseDto = memoService.MemoInfo(memo1.id)
        val findByIdMemo:Memo = memoRepository.findById(memo1.id)

        Assert.assertEquals(responseMemo.title,findByIdMemo.title)
        Assert.assertEquals(responseMemo.text, findByIdMemo.text)
        Assert.assertEquals(responseMemo.createdAt,Dateformatting(findByIdMemo.createdAt))

    }

    @Test
    fun delete(){
        var memo:Memo = Memo(id = 4L, title = "memo_title", text = "memo_text")
        memoRepository.save(memo)

        memoRepository.delete(1L)

       verify(memoRepository).delete(anyLong())
    }

    @Test
    fun updateMemo(){
        var memo:Memo = Memo(id = 5L, title = "memo_title", text = "memo_text")

        val updateDto: MemoCreateUpdateDto = MemoCreateUpdateDto("update_title","update_text")

        var updatedMemo:Memo = Memo(id = 5L, title = "update_title", text = "update_text", updatedAt = LocalDateTime.now())

        `when`(memoRepository.findById(memo.id)).thenReturn(updatedMemo)
        `when`(memoRepository.update(updateDto,5L)).thenReturn(updatedMemo)

        val result : MemoPreviewDto = memoService.updateMemo(5L,updateDto)
        val findByIdMemo: Memo = memoRepository.findById(5L)

        Assert.assertEquals(result.title,findByIdMemo.title)
        Assert.assertEquals(result.createdAt,Dateformatting(findByIdMemo.createdAt))
        Assert.assertEquals(result.updatedAt,Dateformatting(findByIdMemo.updatedAt))
    }

    @Test
    fun MemoInfoByDate(){
        var memoList:ArrayList<Memo> = ArrayList()

        var memo1: Memo = Memo(id = 6L, title = "memo1_title", text = "memo1_text")
        memoList.add(memo1)

        var memo2:Memo = Memo(id = 7L, title = "memo2_title", text = "memo2_text")
        memoList.add(memo2)


        var memo3:Memo = Memo(id = 8L, title = "memo3_title", text = "memo3_text")
        memoList.add(memo3)

        `when`(memoRepository.MemoInfoByDate(LocalDate.of(2020,10,15),1)).thenReturn(memoList)

        var resultList :List<MemoPreviewDto> = memoService.MemoInfoByDate(LocalDate.of(2020,10,15),1)
        var repositoryList: List<Memo> = memoRepository.MemoInfoByDate(LocalDate.of(2020,10,15),1)


        assertThat(resultList.size).isEqualTo(repositoryList.size)
        Assert.assertSame(repositoryList,memoList)
    }
}