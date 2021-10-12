package com.incubate.kotlinmemo

import com.incubate.kotlinmemo.repository.MemoMemoryRepository
import com.incubate.kotlinmemo.service.MemoService
import com.incubate.kotlinmemo.service.MemoServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.sql.DataSource

@Configuration
class ConfigurationBean(private val dataSource: DataSource, private var em: EntityManager) {
    @Bean
    fun memoService():MemoService{ return MemoServiceImpl(memoRepository()) }
    @Bean
    fun memoRepository(): MemoMemoryRepository { return MemoMemoryRepository(em)}
}