package com.github.arlekinside.slg.core.services.balance

import com.github.arlekinside.slg.core.entities.User

interface BalanceProcessor {

    suspend fun submitBalance(userId: Int, amount: Int): User?

}