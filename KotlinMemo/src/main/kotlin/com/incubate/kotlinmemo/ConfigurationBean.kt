package com.incubate.kotlinmemo

import com.incubate.kotlinmemo.repository.MemoMemoryRepository
import com.incubate.kotlinmemo.repository.MemoRepository
import com.incubate.kotlinmemo.service.MemoService
import com.incubate.kotlinmemo.service.MemoServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManager
import javax.sql.DataSource

@Configuration
class ConfigurationBean(private val dataSource: DataSource, private val em: EntityManager) {
    @Bean
    fun memoService() = MemoServiceImpl(memoRepository())
    @Bean
    fun memoRepository() = MemoMemoryRepository(em)
}