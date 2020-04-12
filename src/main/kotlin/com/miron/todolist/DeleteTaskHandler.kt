package com.miron.todolist

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.miron.todolist.extension.logger
import com.miron.todolist.model.DeleteTaskRequest
import com.miron.todolist.service.TaskService
import javax.inject.Inject
import javax.inject.Named

@Named("delete-task-by-id")
class DeleteTaskHandler @Inject constructor(private val taskService: TaskService) : RequestHandler<DeleteTaskRequest, Void?> {

    override fun handleRequest(input: DeleteTaskRequest, context: Context): Void? {
        val uuid = input.id
        log.info("Deleting task with id {}", uuid)

        taskService.delete(uuid)

        return null
    }

    companion object {
        private val log = logger<DeleteTaskHandler>()
    }

}