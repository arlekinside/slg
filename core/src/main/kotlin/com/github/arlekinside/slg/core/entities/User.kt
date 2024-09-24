package com.github.arlekinside.slg.core.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "username")
    var username: String,

    @Column(name = "balance")
    var balance: Double
)