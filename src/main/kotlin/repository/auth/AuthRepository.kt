package repository.auth

import dto.common.UserInfoDto

/**
 * @property registerUser регистрация пользователя
 * @property getUserInfoById получение пользователя по id
 * @property getUserInfoByMail получение пользователя по mail
 * @property getUserInfoOrNullByMail получение пользователя по mail (или null)
 * @property changePassword смена пароля
 *
 * @see AuthRepositoryImpl
 */
interface AuthRepository {
    fun registerUser(mail: String, password: String, surname: String, name: String, patronymic: String)
    fun getUserInfoByMail(mail: String): UserInfoDto
    fun getUserInfoById(id: Int): UserInfoDto
    fun getUserInfoOrNullByMail(mail: String): UserInfoDto?
    fun changePassword(userId: Int, password: String)
}