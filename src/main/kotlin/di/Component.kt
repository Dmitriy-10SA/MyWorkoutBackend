package di

import di.Component.accountService
import di.Component.authService
import di.Component.exerciseService
import di.Component.workoutService
import repository.account.AccountRepositoryImpl
import repository.auth.AuthRepositoryImpl
import repository.exercise.ExerciseRepositoryImpl
import repository.workout.WorkoutRepositoryImpl
import service.AccountService
import service.AuthService
import service.ExerciseService
import service.WorkoutService

/**
 * Компонент для DI (если это можно назвать DI)
 *
 * @property authService
 * @property accountService
 * @property exerciseService
 * @property workoutService
 */
object Component {
    private val authRepository = AuthRepositoryImpl()
    val authService = AuthService(authRepository)

    private val accountRepository = AccountRepositoryImpl()
    val accountService = AccountService(accountRepository)

    private val exerciseRepository = ExerciseRepositoryImpl()
    val exerciseService = ExerciseService(exerciseRepository)

    private val workoutRepository = WorkoutRepositoryImpl()
    val workoutService = WorkoutService(workoutRepository)
}