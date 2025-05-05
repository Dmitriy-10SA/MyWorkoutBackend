package dto.exercise

import kotlinx.serialization.Serializable

/**
 * Мышечная группа
 *
 * @property id идентификатор
 * @property name название
 */
@Serializable
data class GroupDto(
    val id: Int,
    val name: String
)
