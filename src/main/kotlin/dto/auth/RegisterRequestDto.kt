package dto.auth

import kotlinx.serialization.Serializable

/**
 * Запрос к серверу при регистрации
 *
 * @property mail почта
 * @property password пароль
 * @property surname фамилия
 * @property name имя
 * @property patronymic отчество
 */
@Serializable
data class RegisterRequestDto(
    val mail: String,
    val password: String,
    val surname: String,
    val name: String,
    val patronymic: String
)
