import config.configureSecurity
import config.configureSerialization
import config.connectToPostgreSQLDatabase
import io.ktor.server.application.*
import route.configureAccountRoutes
import route.configureAuthRoutes
import route.configureExerciseRoutes
import route.configureWorkoutRoutes

fun main(args: Array<String>) {
    connectToPostgreSQLDatabase()
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureAuthRoutes()
    configureAccountRoutes()
    configureExerciseRoutes()
    configureWorkoutRoutes()
}