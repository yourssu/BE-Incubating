package com.incubate.kotlinmemo.dto

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateSerializer : StdSerializer<LocalDateTime>(LocalDateTime::class.java) {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    override fun serialize(localDateTime: LocalDateTime, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
        jsonGenerator.writeString(formatter.format(localDateTime))
    }

}