package route

import config.MyWorkoutSecurity
import di.Component
import dto.auth.LoginRequestDto
import dto.auth.RegisterRequestDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import service.AuthService

//маршруты для регистрации, проверки токена и входа
fun Application.configureAuthRoutes() {
    val authService = Component.authService

    routing {
        route("/auth") {
            configureRegister(authService)
            configureCheckToken()
            configureLogin(authService)
        }
    }
}

//маршрут для регистрации
private fun Route.configureRegister(authService: AuthService) {
    post("/register") {
        val registerRequestDto = call.receive<RegisterRequestDto>()
        authService.register(registerRequestDto)?.let { call.respond(it) } ?: call.respond(HttpStatusCode.Unauthorized)
    }
}

//маршрут для проверки токена
private fun Route.configureCheckToken() {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("/check-token") {
            call.respond(HttpStatusCode.OK)
        }
    }
}

//маршрут для входа
private fun Route.configureLogin(authService: AuthService) {
    post("/login") {
        val loginRequestDto = call.receive<LoginRequestDto>()
        authService.login(loginRequestDto)?.let { call.respond(it) } ?: call.respond(HttpStatusCode.Unauthorized)
    }
}