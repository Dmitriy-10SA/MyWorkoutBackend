package dto.auth

import kotlinx.serialization.Serializable

/**
 * Запрос к серверу при входе
 *
 * @property mail почта
 * @property password пароль
 */
@Serializable
data class LoginRequestDto(
    val mail: String,
    val password: String
)
