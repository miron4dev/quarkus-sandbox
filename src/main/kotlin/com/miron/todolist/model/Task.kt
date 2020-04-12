package com.miron.todolist.model

import io.quarkus.runtime.annotations.RegisterForReflection
import java.time.LocalDateTime
import java.util.*

@RegisterForReflection
data class Task(
    val id: UUID,
    val name: String,
    val description: String?,
    val createdAt: LocalDateTime
)