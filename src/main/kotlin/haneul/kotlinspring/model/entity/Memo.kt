package haneul.kotlinspring.model.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Memo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,
    var title:String,
    var text:String,
    var createdAt:LocalDateTime,
    var updatedAt:LocalDateTime
)
