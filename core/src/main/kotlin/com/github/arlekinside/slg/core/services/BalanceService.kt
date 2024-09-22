package com.github.arlekinside.slg.core.services

interface BalanceService {

    fun updateBalance(userId: Long, amount: Double): Double
}