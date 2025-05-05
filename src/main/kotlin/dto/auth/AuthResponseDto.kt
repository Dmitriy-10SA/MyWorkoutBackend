package dto.auth

import kotlinx.serialization.Serializable

/**
 * Ответ сервера при успешном входе
 *
 * @property token токен
 */
@Serializable
data class AuthResponseDto(
    val token: String
)