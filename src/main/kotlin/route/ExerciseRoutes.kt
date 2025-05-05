package route

import config.MyWorkoutSecurity
import di.Component
import dto.exercise.ExerciseAddRequestDto
import dto.exercise.ExerciseDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import service.ExerciseService
import service.ServiceUtils.requestWithParamAndTryCatch

//все маршруты для действий над упражнениями и дополнительных действий, связанных с ними
fun Application.configureExerciseRoutes() {
    val exerciseService = Component.exerciseService

    routing {
        route("exercise") {
            configureGetAllMusclesForGroup(exerciseService)
            configureGetAllGroups(exerciseService)
            configureGetAllExerciseTypes(exerciseService)
            configureAddExercise(exerciseService)
            configureGetAllUserExercise(exerciseService)
            configureChangeUserExercise(exerciseService)
            configureRemoveUserExercise(exerciseService)
            configureGetAllExerciseForExerciseType(exerciseService)
            configureGetAllExerciseForMuscle(exerciseService)
            configureGetAllExercise(exerciseService)
            configureGetAllExerciseByName(exerciseService)
        }
    }
}

//маршрут для получения списка мышц по мышечной группе
private fun Route.configureGetAllMusclesForGroup(exerciseService: ExerciseService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("all-muscles") {
            requestWithParamAndTryCatch("groupId") {
                call.respond(exerciseService.getAllMusclesForGroup(it.toInt()))
            }
        }
    }
}

//маршрут для получения списка групп
private fun Route.configureGetAllGroups(exerciseService: ExerciseService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("all-groups") {
            call.respond(exerciseService.getAllGroups())
        }
    }
}

//маршрут для получения списка типов упражнений
private fun Route.configureGetAllExerciseTypes(exerciseService: ExerciseService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("all-exercise-types") {
            call.respond(exerciseService.getAllExerciseTypes())
        }
    }
}

//маршрут для добавления упражнений
private fun Route.configureAddExercise(exerciseService: ExerciseService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        put("add") {
            val userId = call.principal<JWTPrincipal>()!!.payload.getClaim(MyWorkoutSecurity.ID_CLAIM).asInt()
            val exerciseAddRequestDto = call.receive<ExerciseAddRequestDto>()
            exerciseService.addExercise(exerciseAddRequestDto, userId)
            call.respond(HttpStatusCode.OK)
        }
    }
}

//маршрут для получения всех упражнений, созданных пользователем по id
private fun Route.configureGetAllUserExercise(exerciseService: ExerciseService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("user-exercises") {
            val userId = call.principal<JWTPrincipal>()!!.payload.getClaim(MyWorkoutSecurity.ID_CLAIM).asInt()
            call.respond(exerciseService.getAllUserExercise(userId))
        }
    }
}

//маршрут для изменения упражнения пользователя
private fun Route.configureChangeUserExercise(exerciseService: ExerciseService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        put("change") {
            val userId = call.principal<JWTPrincipal>()!!.payload.getClaim(MyWorkoutSecurity.ID_CLAIM).asInt()
            val exerciseDto = call.receive<ExerciseDto>()
            exerciseService.changeUserExercise(exerciseDto, userId)
            call.respond(HttpStatusCode.OK)
        }
    }
}

//маршрут для удаления упражнения пользователя
private fun Route.configureRemoveUserExercise(exerciseService: ExerciseService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        delete("delete") {
            requestWithParamAndTryCatch("exerciseId") {
                exerciseService.removeUserExercise(it.toLong())
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}

//маршрут получения списка упражнений для конкретного типа упражнения
private fun Route.configureGetAllExerciseForExerciseType(exerciseService: ExerciseService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("all-for-exercise-type") {
            requestWithParamAndTryCatch("exerciseTypeId") {
                call.respond(exerciseService.getAllExerciseForExerciseType(it.toInt()))
            }
        }
    }
}

//маршрут получения списка упражнений для конкретной мышцы
private fun Route.configureGetAllExerciseForMuscle(exerciseService: ExerciseService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("all-for-muscle") {
            requestWithParamAndTryCatch("muscleId") {
                call.respond(exerciseService.getAllExerciseForMuscle(it.toInt()))
            }
        }
    }
}

//маршрут получения списка всех упражнений
private fun Route.configureGetAllExercise(exerciseService: ExerciseService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("all") {
            call.respond(exerciseService.getAllExercise())
        }
    }
}

//маршрут для получения списка всех упражнений, подходящих (или похожих) по названию
private fun Route.configureGetAllExerciseByName(exerciseService: ExerciseService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("all-by-name") {
            requestWithParamAndTryCatch("name") {
                call.respond(exerciseService.getAllExerciseByName(it))
            }
        }
    }
}