package com.incubate.kotlinmemo.dto

import lombok.NonNull
import java.lang.IllegalStateException
import kotlin.jvm.internal.Intrinsics

class MemoCreateUpdateDto {
    lateinit var text:String
    lateinit var title:String

    fun nullCheck(){
        if(!(::text.isInitialized)){
            Intrinsics.throwUninitializedProperty("메모내용을 입력하세요")
        } else if(!(::title.isInitialized)){
            Intrinsics.throwUninitializedProperty("메모제목을 입력하세요")
        }
    }

}