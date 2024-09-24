package com.github.arlekinside.slg.core.services

import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class BalanceServiceImpl: BalanceService {

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    override fun updateBalance(userId: Long, amount: Double): Double {
        TODO("Not yet implemented")
    }

}