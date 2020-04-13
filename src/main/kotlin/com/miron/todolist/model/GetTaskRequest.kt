package com.miron.todolist.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.runtime.annotations.RegisterForReflection
import java.util.UUID

@RegisterForReflection
data class GetTaskRequest @JsonCreator constructor(
        @JsonProperty("userId")
        val userId: UUID
)