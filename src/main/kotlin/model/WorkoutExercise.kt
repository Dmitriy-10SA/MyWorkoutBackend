package model

import model.WorkoutExercise.exerciseId
import model.WorkoutExercise.id
import model.WorkoutExercise.reps
import model.WorkoutExercise.sets
import model.WorkoutExercise.workoutId
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

/**
 * Таблица WorkoutExercise (упражнение на тренировку)
 *
 * @property id идентификатор (автоинкрементируемый)
 * @property sets кол-во подходов
 * @property reps кол-во повторений
 * @property workoutId id тренировки
 * @property exerciseId id упражнения
 */
object WorkoutExercise : Table("workout_exercise") {
    val id = long("id").autoIncrement()
    val sets = integer("sets").nullable()
    val reps = integer("reps").nullable()
    val workoutId = long("workout_id").references(
        ref = Workout.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val exerciseId = long("exercise_id").references(
        ref = Exercise.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )

    override val primaryKey = PrimaryKey(id)
}