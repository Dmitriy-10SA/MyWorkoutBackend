package service

import dto.workout.WorkoutAddRequestDto
import dto.workout.WorkoutExerciseAddRequestDto
import kotlinx.datetime.LocalDate
import repository.workout.WorkoutRepository

/**
 * @property getWorkoutByDate получение списка тренировок на дату
 * @property addWorkout добавление тренировки
 * @property removeWorkout удаление тренировки
 * @property addWorkoutExercise добавление упражнения для тренировки
 * @property removeWorkoutExercise удаление упражнения для тренировки
 * @property getWorkoutExercisesByWorkoutId получение списка упражнений для тренировки по id тренировки
 *
 * @see WorkoutRepository
 */
class WorkoutService(
    private val workoutRepository: WorkoutRepository
) {
    fun getWorkoutByDate(date: LocalDate) = workoutRepository.getWorkoutByDate(date)
    fun addWorkout(workoutAddRequestDto: WorkoutAddRequestDto, userId: Int) = workoutRepository.addWorkout(
        workoutAddRequestDto.name,
        workoutAddRequestDto.date,
        userId
    )

    fun removeWorkout(workoutId: Long) = workoutRepository.removeWorkout(workoutId)
    fun addWorkoutExercise(
        workoutExerciseAddRequestDto: WorkoutExerciseAddRequestDto
    ) = workoutRepository.addWorkoutExercise(
        workoutExerciseAddRequestDto.sets,
        workoutExerciseAddRequestDto.reps,
        workoutExerciseAddRequestDto.workoutId,
        workoutExerciseAddRequestDto.exerciseId
    )

    fun removeWorkoutExercise(workoutExerciseId: Long) = workoutRepository.removeWorkoutExercise(workoutExerciseId)
    fun getWorkoutExercisesByWorkoutId(workoutId: Long) = workoutRepository.getWorkoutExercisesByWorkoutId(workoutId)
}