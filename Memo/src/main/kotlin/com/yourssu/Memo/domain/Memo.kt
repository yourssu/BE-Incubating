package com.yourssu.Memo.domain

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Entity
@Getter
@Setter
class Memo {

    @Id
    @GeneratedValue
    val id : Long? = null
    var title : String? = null
    @Lob
    var text : String? = null

    @Temporal(TemporalType.TIMESTAMP)
    var createdAt = Date()

    @Temporal(TemporalType.TIMESTAMP)
    var updatedAt = Date()
}