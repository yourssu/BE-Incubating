package com.incubate.kotlinmemo.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.incubate.kotlinmemo.dto.DateSerializer
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Memo(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0,
    @Column(unique = true)
    var title:String,
    @Column(unique = true)
    var text:String,
    @Column
//    @JsonSerialize(using = DateSerializer::class)
    var updatedAt:LocalDateTime = LocalDateTime.now(),
    @Column
//    @JsonSerialize(using = DateSerializer::class)
    val createdAt:LocalDateTime = LocalDateTime.now()
)