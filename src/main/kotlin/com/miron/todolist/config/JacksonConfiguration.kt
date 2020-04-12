package com.miron.todolist.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.quarkus.arc.Unremovable
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces

@ApplicationScoped
class JacksonConfiguration {

    @Produces
    @Unremovable
    fun objectMapper(): ObjectMapper {
        val objectMapper = jacksonObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        return objectMapper
    }
}