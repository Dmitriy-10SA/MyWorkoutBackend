package dto.workout

import kotlinx.serialization.Serializable

/**
 * Упражнение на тренировку
 *
 * @property id идентификатор (автоинкрементируемый)
 * @property sets кол-во подходов
 * @property reps кол-во повторений
 * @property workoutId id тренировки
 * @property exerciseId id упражнения
 */
@Serializable
data class WorkoutExerciseDto(
    val id: Long,
    val sets: Int?,
    val reps: Int?,
    val workoutId: Long,
    val exerciseId: Long
)
