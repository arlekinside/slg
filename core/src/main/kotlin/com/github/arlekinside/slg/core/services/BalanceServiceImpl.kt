package com.github.arlekinside.slg.core.services

import com.github.arlekinside.slg.core.aspects.Timed
import com.github.arlekinside.slg.core.services.balance.BalanceProcessor
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class BalanceServiceImpl(
    private val processor: BalanceProcessor,
) : BalanceService {

    private val log: Logger = LoggerFactory.getLogger(BalanceServiceImpl::class.java)

    @Timed
    override fun updateBalance(data: Map<Int, Int>): Map<Int, Int> = runBlocking {
        // awaitAll() might not be that meaningful here, but as long as the external communication is synchronous, there's not much we can do here
        log.info("Updating balance for ${data.size} users")

        val result = data.map {
            async {
                processor.submitBalance(it.key, it.value)
            }
        }.awaitAll().filterNotNull().associate { it.id to it.balance }

        log.info("Balance updated for ${result.size} users")

        return@runBlocking result
    }

}