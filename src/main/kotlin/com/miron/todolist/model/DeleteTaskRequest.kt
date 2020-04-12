package com.miron.todolist.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.runtime.annotations.RegisterForReflection
import java.util.*

@RegisterForReflection
data class DeleteTaskRequest @JsonCreator constructor(
        @JsonProperty("id")
        val id: UUID
)