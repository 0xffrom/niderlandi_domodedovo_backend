package com.niderlandi.domodedovo.domain.entity

import javax.persistence.*

@Entity
class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @JoinColumn(name = "name")
    val name : String = "",

    @JoinColumn(name = "lastName")
    val lastName : String = ""
)