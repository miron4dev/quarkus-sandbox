package com.miron.todolist.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.quarkus.arc.Unremovable
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces

@ApplicationScoped
class JacksonConfiguration {

    @Produces
    @Unremovable
    fun objectMapper() = jacksonObjectMapper()
}