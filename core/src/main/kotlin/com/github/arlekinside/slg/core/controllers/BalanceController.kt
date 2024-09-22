package com.github.arlekinside.slg.core.controllers

import com.github.arlekinside.slg.core.services.BalanceService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/balance")
class BalanceController(
    val balanceService: BalanceService
) {

    @PostMapping
    fun updateBalance(@RequestBody data: Map<Long, Double>) {
        data.forEach { (id, balance) -> balanceService.updateBalance(id, balance) }
    }

}