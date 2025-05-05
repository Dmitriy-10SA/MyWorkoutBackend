package repository.workout

import dto.workout.WorkoutDto
import dto.workout.WorkoutExerciseDto
import kotlinx.datetime.LocalDate

/**
 * @property getWorkoutByDate получение списка тренировок на дату
 * @property addWorkout добавление тренировки
 * @property removeWorkout удаление тренировки
 * @property addWorkoutExercise добавление упражнения для тренировки
 * @property removeWorkoutExercise удаление упражнения для тренировки
 * @property getWorkoutExercisesByWorkoutId получение списка упражнений для тренировки по id тренировки
 *
 * @see WorkoutRepositoryImpl
 */
interface WorkoutRepository {
    fun getWorkoutByDate(date: LocalDate): List<WorkoutDto>
    fun addWorkout(name: String?, date: LocalDate, ownerUserId: Int)
    fun removeWorkout(workoutId: Long)
    fun addWorkoutExercise(sets: Int?, reps: Int?, workoutId: Long, exerciseId: Long)
    fun removeWorkoutExercise(workoutExerciseId: Long)
    fun getWorkoutExercisesByWorkoutId(workoutId: Long): List<WorkoutExerciseDto>
}