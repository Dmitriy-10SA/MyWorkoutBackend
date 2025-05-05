package service

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

object ServiceUtils {
    suspend fun RoutingContext.requestWithParamAndTryCatch(paramName: String, callback: suspend (String) -> Unit) {
        try {
            call.request.queryParameters[paramName]?.let {
                callback(it)
            } ?: call.respond(HttpStatusCode.BadRequest)
        } catch (_: Exception) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}