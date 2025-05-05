package dto.account

import kotlinx.serialization.Serializable

/**
 * Запрос к серверу на изменение информации о пользователе
 *
 * @property surname фамилия
 * @property name имя
 * @property patronymic отчество
 * @property photo фото (может быть null)
 */
@Serializable
data class ChangeUserInfoRequestDto(
    val surname: String,
    val name: String,
    val patronymic: String,
    val photo: String?
)
