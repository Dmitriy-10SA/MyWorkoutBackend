package dto.exercise

import kotlinx.serialization.Serializable

/**
 * Запрос к серверу по созданию упражнения
 *
 * @property name название
 * @property description описание
 * @property video видео
 * @property muscleId id мышцы
 */
@Serializable
data class ExerciseAddRequestDto(
    val name: String,
    val description: String?,
    val video: String?,
    val muscleId: Int?,
    val exerciseTypeId: Int
)
