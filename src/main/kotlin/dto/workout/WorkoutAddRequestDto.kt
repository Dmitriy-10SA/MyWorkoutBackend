package dto.workout

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

/**
 * Запрос на сервер для создания тренировки
 *
 * @property name название
 * @property date дата
 */
@Serializable
data class WorkoutAddRequestDto(
    val name: String?,
    val date: LocalDate
)
