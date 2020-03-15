package com.miron.todolist

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.miron.todolist.extension.logger
import com.miron.todolist.model.Task
import com.miron.todolist.service.TaskService
import javax.inject.Inject
import javax.inject.Named

@Named("create-task")
class CreateTaskHandler @Inject constructor(private val taskService: TaskService) : RequestHandler<Task, Boolean> {

    override fun handleRequest(task: Task, context: Context): Boolean {
        log.info("Creating the new task {}", task);
        taskService.create(task)

        return true
    }

    companion object {
        private val log = logger<CreateTaskHandler>()
    }
}