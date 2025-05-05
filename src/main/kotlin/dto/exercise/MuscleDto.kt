package dto.exercise

import kotlinx.serialization.Serializable

/**
 * Мышца
 *
 * @property id идентификатор
 * @property groupId id мышечной группы
 * @property name название
 */
@Serializable
data class MuscleDto(
    val id: Int,
    val groupId: Int,
    val name: String
)
