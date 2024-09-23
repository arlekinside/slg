package com.github.arlekinside.slg.core.services

import com.github.arlekinside.slg.core.aspects.Timed

interface BalanceService {

    @Timed
    fun updateBalance(data: Map<Int, Int>): Map<Int, Int>

}