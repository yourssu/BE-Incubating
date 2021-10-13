package com.yourssu_incubating.demo.entity.memo

import com.yourssu_incubating.demo.entity.BaseTimeEntity
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Entity
data class Memos (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var title: String? = null,

    @Column(nullable = false, columnDefinition = "TEXT")
    var text: String? = null,

): BaseTimeEntity() {
    private val pattern: String = "yyyy-MM-dd HH:mm:ss"

    fun update(title: String, text: String) {
        this.title = title;
        this.text = text;
    }
    fun getStringCreatedAt(): String {
        return createdAt!!.format(DateTimeFormatter.ofPattern(pattern))
    }
    fun getStringUpdatedAt(): String {
        return updatedAt!!.format(DateTimeFormatter.ofPattern(pattern))
    }
}