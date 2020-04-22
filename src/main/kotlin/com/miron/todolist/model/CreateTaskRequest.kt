package com.miron.todolist.model

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class CreateTaskRequest(val name: String, val description: String?)