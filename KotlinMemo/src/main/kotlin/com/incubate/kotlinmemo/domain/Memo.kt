package com.incubate.kotlinmemo.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.incubate.kotlinmemo.dto.DateSerializer
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.*

@Entity
class Memo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,
    @Column(unique = true)
    var title:String,
    @Column(unique = true)
    var text:String,
    @Column     @JsonSerialize(using = DateSerializer::class)
    var updatedAt:LocalDateTime = LocalDate.now().atStartOfDay(),
    @Column     @JsonSerialize(using = DateSerializer::class)
    val createdAt:LocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0))
)