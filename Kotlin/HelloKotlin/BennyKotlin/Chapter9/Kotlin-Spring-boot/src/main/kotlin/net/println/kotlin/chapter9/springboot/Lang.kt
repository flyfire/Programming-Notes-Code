package net.println.kotlin.chapter9.springboot

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by benny on 4/30/17.
 */
@Entity
data class Lang(
        var name: String,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1)