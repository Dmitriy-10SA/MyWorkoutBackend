package dto.common

import kotlinx.serialization.Serializable

/**
 * Информация о пользователе
 *
 * @property id идентификатор (автоинкрементируемый)
 * @property mail почта
 * @property password пароль (хэшированный)
 * @property surname фамилия
 * @property name имя
 * @property patronymic отчество
 * @property photo фото (может быть null)
 */
@Serializable
data class UserInfoDto(
    val id: Int,
    val mail: String,
    val password: String,
    val surname: String,
    val name: String,
    val patronymic: String,
    val photo: String?
)