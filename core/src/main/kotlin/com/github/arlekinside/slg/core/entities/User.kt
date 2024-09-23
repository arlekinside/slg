package com.github.arlekinside.slg.core.entities

import jakarta.persistence.Entity
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Entity
@Table(name = "users")
class User(

    @jakarta.persistence.Id
    @Id
    var id: Int,

    @Column
    var balance: Int = 0

) {

    @Column
    var name: String = "username$id"

}