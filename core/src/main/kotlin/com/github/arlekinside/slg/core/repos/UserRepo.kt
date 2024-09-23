package com.github.arlekinside.slg.core.repos

import com.github.arlekinside.slg.core.entities.User
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.relational.core.sql.LockMode
import org.springframework.data.relational.repository.Lock
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : CoroutineCrudRepository<User, Int> {

    @Lock(LockMode.PESSIMISTIC_READ)
    override suspend fun existsById(id: Int): Boolean

    @Modifying
    @Query("UPDATE users SET balance = $2 WHERE id = $1")
    suspend fun updateUserByBalanceById(userId: Int, balance: Int): Long

    @Modifying
    @Query("INSERT INTO users (id, name, balance) VALUES ($1, $2, $3)")
    suspend fun insertUser(userId: Int, name: String, balance: Int): Long

}