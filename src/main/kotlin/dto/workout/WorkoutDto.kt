package dto.workout

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

/**
 * Тренировка
 *
 * @property id идентификатор (автоинкрементируемый)
 * @property name название
 * @property date дата
 * @property ownerUserId id пользователя-владельца
 */
@Serializable
data class WorkoutDto(
    val id: Long,
    val name: String?,
    val date: LocalDate,
    val ownerUserId: Int
)
