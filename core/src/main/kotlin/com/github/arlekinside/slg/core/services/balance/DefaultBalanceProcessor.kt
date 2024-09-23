package com.github.arlekinside.slg.core.services.balance

import com.github.arlekinside.slg.core.entities.User
import com.github.arlekinside.slg.core.repos.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

open class DefaultBalanceProcessor(
    private val userRepo: UserRepo
) : BalanceProcessor {

    private var log: Logger = LoggerFactory.getLogger(DefaultBalanceProcessor::class.java)

    // Make sure each balance update is independent
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override suspend fun submitBalance(userId: Int, amount: Int): User? = withContext(Dispatchers.IO) {
        val user = User(userId, amount)
        try {

            val updateCount = if (userRepo.existsById(user.id)) {
                userRepo.updateUserByBalanceById(user.id, user.balance)
            } else {
                // userRepo.save() behaves strange for pre-defined IDs https://github.com/spring-projects/spring-data-r2dbc/issues/738
                userRepo.insertUser(user.id, user.name, user.balance)
            }

            if (updateCount <= 0) {
                log.warn("Failed to update balance for user #$userId")
                return@withContext null
            }

            return@withContext user
        } catch (e: Exception) {
            log.warn("Error while updating balance for user #$userId", e)
            return@withContext null
        }
    }

}