package com.github.arlekinside.slg.core.repos

import com.github.arlekinside.slg.core.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo: JpaRepository<User, Long>