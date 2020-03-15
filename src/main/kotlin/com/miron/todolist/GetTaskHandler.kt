package com.miron.todolist

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.miron.todolist.extension.logger
import com.miron.todolist.model.Task
import com.miron.todolist.service.TaskService
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@Named("get-task-by-id")
class GetTaskHandler @Inject constructor(private val taskService: TaskService) : RequestHandler<UUID, Task?> {

    override fun handleRequest(input: UUID, context: Context): Task? {
        log.info("Fetching task by id {}", input)
        return taskService.get(input)
    }

    companion object {
        private val log = logger<GetTaskHandler>()
    }

}