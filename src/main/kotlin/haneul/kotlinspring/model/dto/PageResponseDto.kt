package haneul.kotlinspring.model.dto

import haneul.kotlinspring.model.entity.Memo
import org.springframework.data.domain.Page

class PageResponseDto (memoList: List<SimpleResponseDto>, page: Page<Memo>) {
    val memos = memoList
    val size = page.size
    val totalElements = page.totalElements
    val totalPages = page.totalPages
}