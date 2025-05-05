package route

import config.MyWorkoutSecurity
import di.Component
import dto.workout.WorkoutAddRequestDto
import dto.workout.WorkoutExerciseAddRequestDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.LocalDate
import service.ServiceUtils.requestWithParamAndTryCatch
import service.WorkoutService

//все маршруты для действий над тренировками и дополнительных действий, связанных с ними
fun Application.configureWorkoutRoutes() {
    val workoutService = Component.workoutService

    routing {
        route("workout") {
            configureGetWorkoutByDate(workoutService)
            configureAddWorkout(workoutService)
            configureRemoveWorkout(workoutService)
            configureAddWorkoutExercise(workoutService)
            configureRemoveWorkoutExercise(workoutService)
            configureGetWorkoutExercisesByWorkoutId(workoutService)
        }
    }
}

//маршрут для получения списка тренировок на дату
private fun Route.configureGetWorkoutByDate(workoutService: WorkoutService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("by-date") {
            requestWithParamAndTryCatch("date") {
                call.respond(workoutService.getWorkoutByDate(LocalDate.parse(it)))
            }
        }
    }
}

//маршрут для добавления тренировки
private fun Route.configureAddWorkout(workoutService: WorkoutService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        put("add") {
            val userId = call.principal<JWTPrincipal>()!!.payload.getClaim(MyWorkoutSecurity.ID_CLAIM).asInt()
            val workoutAddRequestDto = call.receive<WorkoutAddRequestDto>()
            workoutService.addWorkout(workoutAddRequestDto, userId)
            call.respond(HttpStatusCode.OK)
        }
    }
}

//маршрут для удаления тренировки
private fun Route.configureRemoveWorkout(workoutService: WorkoutService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        delete("delete-workout") {
            requestWithParamAndTryCatch("workoutId") {
                workoutService.removeWorkout(it.toLong())
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}

//маршрут для добавления упражнения для тренировки
private fun Route.configureAddWorkoutExercise(workoutService: WorkoutService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        put("add-workout-exercise") {
            val workoutExerciseAddRequestDto = call.receive<WorkoutExerciseAddRequestDto>()
            workoutService.addWorkoutExercise(workoutExerciseAddRequestDto)
            call.respond(HttpStatusCode.OK)
        }
    }
}

//маршрут для удаления упражнения для тренировки
private fun Route.configureRemoveWorkoutExercise(workoutService: WorkoutService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        delete("delete-workout-exercise") {
            requestWithParamAndTryCatch("workoutExerciseId") {
                workoutService.removeWorkoutExercise(it.toLong())
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}

//маршрут для получения списка упражнений для тренировки по id тренировки
private fun Route.configureGetWorkoutExercisesByWorkoutId(workoutService: WorkoutService) {
    authenticate(MyWorkoutSecurity.USER_AUTH_JWT) {
        get("workout-exercises") {
            requestWithParamAndTryCatch("workoutId") {
                call.respond(workoutService.getWorkoutExercisesByWorkoutId(it.toLong()))
            }
        }
    }
}