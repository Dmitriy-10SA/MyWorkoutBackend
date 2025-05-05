package route

import config.MyWorkoutSecurity
import di.Component
import dto.account.ChangeUserInfoRequestDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import service.AccountService

//маршруты для получения инф-ии об аккаунте и изменения данных аккаунта
fun Application.configureAccountRoutes() {
    val accountService = Component.accountService

    routing {
        route("/account") {
            configureGetInfo(accountService)
            configureChangeInfo(accountService)
        }
    }
}

//маршрут для получения информации об аккаунте
private fun Route.configureGetInfo(accountService: AccountService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("/info") {
            val userId = call.principal<JWTPrincipal>()!!.payload.getClaim(MyWorkoutSecurity.ID_CLAIM).asInt()
            val userInfoDto = accountService.getInfo(userId)
            call.respond(userInfoDto)
        }
    }
}

//маршрут для изменения данных аккаунта
private fun Route.configureChangeInfo(accountService: AccountService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        put("change-info") {
            val userId = call.principal<JWTPrincipal>()!!.payload.getClaim(MyWorkoutSecurity.ID_CLAIM).asInt()
            val changeUserInfoRequestDto = call.receive<ChangeUserInfoRequestDto>()
            val surname = changeUserInfoRequestDto.surname
            val name = changeUserInfoRequestDto.name
            val patronymic = changeUserInfoRequestDto.patronymic
            val photo = changeUserInfoRequestDto.photo
            accountService.changeInfo(userId, surname, name, patronymic, photo)
            call.respond(HttpStatusCode.OK)
        }
    }
}