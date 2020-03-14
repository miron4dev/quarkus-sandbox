package com.miron.todolist

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.miron.todolist.extension.logger
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TestHandler : RequestHandler<String, String> {

    override fun handleRequest(input: String, context: Context): String {
        log.info("Handling next request {} with context {}", input, context);
        return "pong message";
    }

    companion object {
        private val log = logger<TestHandler>()
    }
}