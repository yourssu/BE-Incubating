package com.yourssu_incubating.demo.service

import com.yourssu_incubating.demo.entity.memo.MemosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemoService {

    @Autowired
    private lateinit var memoRepository: MemosRepository;

    @Transactional
    fun saveMemo(requiredDto ) {

    }
}