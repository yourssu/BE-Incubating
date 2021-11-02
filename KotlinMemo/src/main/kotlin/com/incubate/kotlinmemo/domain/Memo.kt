package com.incubate.kotlinmemo.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Memo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,
    @Column(unique = true)
    var title:String,
    @Column(unique = true)
    var text:String,
    @Column
    var updatedAt:LocalDateTime = LocalDateTime.now(),
    @Column
    val createdAt:LocalDateTime = LocalDateTime.now()
)