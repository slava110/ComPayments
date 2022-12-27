package com.slava_110.compayments.api

import com.slava_110.compayments.api.v1.apiV1
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.api() {
    routing {
        route("api") {
            apiV1()
        }
    }
}