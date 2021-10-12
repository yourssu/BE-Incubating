package com.incubate.kotlinmemo.domain

import lombok.Getter
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Memo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0
    @Column
    lateinit var title:String
    @Column
    lateinit var text:String
    @Column
    var updatedAt:LocalDateTime = LocalDateTime.now()
    @Column
    val createdAt:LocalDateTime = LocalDateTime.now()

}