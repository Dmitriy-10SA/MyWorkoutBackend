package dto.auth

import kotlinx.serialization.Serializable

/**
 * Запрос к серверу на изменение пароля
 *
 * @property mail почта
 * @property password пароль
 */
@Serializable
data class PasswordChangeRequestDto(
    val mail: String,
    val password: String
)
