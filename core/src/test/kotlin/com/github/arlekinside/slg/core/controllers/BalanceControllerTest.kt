package com.github.arlekinside.slg.core.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.arlekinside.slg.core.CoreApplicationTests
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@AutoConfigureMockMvc
class BalanceControllerTest(
    @Autowired private val mockMvc: MockMvc,
): CoreApplicationTests() {
    private val mapper: ObjectMapper = ObjectMapper()

    @Test
    fun `should update balance for valid input`() {
        val input = mapOf(1 to 100, 2 to 200)
        val inputJson = mapper.writeValueAsString(input)

        mockMvc.post("/balance/set-users-balance") {
            contentType = MediaType.APPLICATION_JSON
            content = inputJson
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(inputJson) }
        }
    }

    @Test
    fun `return 400 for invalid input`() {
        val input = mapOf("smth" to 100)
        val inputJson = mapper.writeValueAsString(input)

        mockMvc.post("/balance/set-users-balance") {
            contentType = MediaType.APPLICATION_JSON
            content = inputJson
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `return 200 on empty input`() {
        val input = emptyMap<Int, Int>()
        val inputJson = mapper.writeValueAsString(input)

        mockMvc.post("/balance/set-users-balance") {
            contentType = MediaType.APPLICATION_JSON
            content = inputJson
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(inputJson) }
        }
    }

    @Test
    fun `return 400 on null input`() {
        mockMvc.post("/balance/set-users-balance") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }
    }

    @Test
    fun `should handle big input`() {
        val input = (1..10_000).associateWith { (1..100_000).random() }
        val inputJson = mapper.writeValueAsString(input)

        mockMvc.post("/balance/set-users-balance") {
            contentType = MediaType.APPLICATION_JSON
            content = inputJson
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(inputJson) }
        }
    }

    @Disabled("Takes up to 3 minutes to complete")
    @Test
    fun `should handle large input`() {
        val input = (1..100_000).associateWith { (1..100_000).random() }
        val inputJson = mapper.writeValueAsString(input)

        mockMvc.post("/balance/set-users-balance") {
            contentType = MediaType.APPLICATION_JSON
            content = inputJson
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(inputJson) }
        }
    }
}