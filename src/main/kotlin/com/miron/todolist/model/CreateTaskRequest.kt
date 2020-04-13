package com.miron.todolist.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.runtime.annotations.RegisterForReflection
import java.util.UUID

// Jackson annotations are required to make it work in native mode
// https://github.com/quarkusio/quarkus/issues/3954
@RegisterForReflection
data class CreateTaskRequest @JsonCreator constructor(
        @JsonProperty("userId")
        val userId: UUID,

        @JsonProperty("name")
        val name: String,

        @JsonProperty("description")
        val description: String?
)