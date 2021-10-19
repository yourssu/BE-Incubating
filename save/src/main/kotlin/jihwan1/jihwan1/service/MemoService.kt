package jihwan1.jihwan1.service

import javassist.NotFoundException
import jihwan1.jihwan1.domain.Memo
import jihwan1.jihwan1.dto.Detail
import jihwan1.jihwan1.dto.Request
import jihwan1.jihwan1.dto.Simple
import jihwan1.jihwan1.repository.MemoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.jvm.Throws

@Service
class MemoService(private val memoRepository: MemoRepository) {

    @Throws(NoSuchFieldError::class)
    fun create(request:Request) : Detail{
        if(request.title.isNullOrEmpty() || request.text.isNullOrEmpty())
            throw NoSuchFieldException()
//        val memo = Memo(_title = request.title, _text = request.text)
        val memo = Memo(title = request.title, text = request.text)
        memoRepository.save(memo)
        return Detail(memo.id as Long, memo.title, memo.text, memo.created as LocalDateTime, memo.updated as LocalDateTime);

    }

    @Transactional
    @Throws(NotFoundException::class)
    fun update(id:Long,request: Request) : Simple{
        val memo = memoRepository.findByIdOrNull(id)
                ?:let { throw NotFoundException("Not Found") }
        memo.title = request.title
        memo.text = request.text
        return Simple(memo.id as Long,memo.title,memo.created as LocalDateTime,memo.updated as LocalDateTime)
    }

    @Throws(NotFoundException::class)
    fun getDetail(id: Long) : Detail{
        val memo = memoRepository.findByIdOrNull(id)
                ?:let { throw NotFoundException("Not Found")}

        return Detail(memo.id as Long,memo.title,memo.text,memo.created as LocalDateTime,memo.updated as LocalDateTime)
    }

    @Throws(NotFoundException::class)
    fun delete(id: Long){
        val memo = memoRepository.findByIdOrNull(id)
                ?:let{throw NotFoundException("Not Fund")}

        memoRepository.deleteById(id)
    }
}