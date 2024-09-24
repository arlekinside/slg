package com.github.arlekinside.slg.core.services.balance

import com.github.arlekinside.slg.core.entities.User

class KafkaBalanceProcessor : BalanceProcessor {

    override suspend fun submitBalance(userId: Int, amount: Int): User? {
        TODO("Not yet implemented")
        // it's an overkill in the scope
    }

}