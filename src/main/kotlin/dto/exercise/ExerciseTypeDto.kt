package dto.exercise

import kotlinx.serialization.Serializable

/**
 * Тип упражнения
 *
 * @property id идентификатор
 * @property name название
 */
@Serializable
data class ExerciseTypeDto(
    val id: Int,
    val name: String
)