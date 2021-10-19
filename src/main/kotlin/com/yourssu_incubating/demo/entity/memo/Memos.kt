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

    fun update(title: String, text: String) {
        this.title = title;
        this.text = text;
    }
}