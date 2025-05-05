package repository.workout

import dto.workout.WorkoutDto
import dto.workout.WorkoutExerciseDto
import kotlinx.datetime.LocalDate
import model.Workout
import model.WorkoutExercise
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

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
class WorkoutRepositoryImpl : WorkoutRepository {
    override fun getWorkoutByDate(date: LocalDate) = transaction {
        Workout.selectAll().where(Workout.date eq date).map {
            WorkoutDto(
                id = it[Workout.id],
                name = it[Workout.name],
                date = it[Workout.date],
                ownerUserId = it[Workout.ownerUserId]
            )
        }
    }

    override fun addWorkout(name: String?, date: LocalDate, ownerUserId: Int) {
        transaction {
            Workout.insert {
                it[Workout.name] = name
                it[Workout.date] = date
                it[Workout.ownerUserId] = ownerUserId
            }
        }
    }

    override fun removeWorkout(workoutId: Long) {
        transaction {
            Workout.deleteWhere { Workout.id eq workoutId }
        }
    }

    override fun addWorkoutExercise(sets: Int?, reps: Int?, workoutId: Long, exerciseId: Long) {
        transaction {
            WorkoutExercise.insert {
                it[WorkoutExercise.sets] = sets
                it[WorkoutExercise.reps] = reps
                it[WorkoutExercise.workoutId] = workoutId
                it[WorkoutExercise.exerciseId] = exerciseId
            }
        }
    }

    override fun removeWorkoutExercise(workoutExerciseId: Long) {
        transaction {
            WorkoutExercise.deleteWhere { WorkoutExercise.id eq workoutExerciseId }
        }
    }

    override fun getWorkoutExercisesByWorkoutId(workoutId: Long) = transaction {
        WorkoutExercise.selectAll().where(WorkoutExercise.workoutId eq workoutId).map {
            WorkoutExerciseDto(
                id = it[WorkoutExercise.id],
                sets = it[WorkoutExercise.sets],
                reps = it[WorkoutExercise.reps],
                workoutId = it[WorkoutExercise.workoutId],
                exerciseId = it[WorkoutExercise.exerciseId]
            )
        }
    }
}