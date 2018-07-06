package net.println.kotlin.chapter9.springboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * Created by benny on 4/30/17.
 */
@RestController
class LangController(val repo: LangRepo){

    // /id/1
    @GetMapping("/id/{id}")
    fun findLangById(@PathVariable id: Long) = repo.findById(id)

    @GetMapping("/name/{name}")
    fun findLangByName(@PathVariable name: String) = repo.findByName(name)

    @GetMapping("/")
    fun findAll() = repo.findAll()
}