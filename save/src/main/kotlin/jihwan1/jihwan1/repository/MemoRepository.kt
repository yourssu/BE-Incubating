package jihwan1.jihwan1.repository

import jihwan1.jihwan1.domain.Memo
import org.springframework.data.jpa.repository.JpaRepository

interface MemoRepository : JpaRepository<Memo,Long> {
}