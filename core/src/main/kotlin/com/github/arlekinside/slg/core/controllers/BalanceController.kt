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

    @PostMapping("/set-users-balance")
    // The map could be replaced with an InputStream in case a million of users is to be updated
    fun updateBalance(@RequestBody data: Map<Int, Int>): Map<Int, Int> = balanceService.updateBalance(data)

}