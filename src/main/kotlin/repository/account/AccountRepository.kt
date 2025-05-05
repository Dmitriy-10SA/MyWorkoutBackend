package repository.account

import dto.common.UserInfoDto

/**
 * @property getInfo получение информации о пользователе
 * @property changeInfo изменение информации о пользователе
 *
 * @see AccountRepositoryImpl
 */
interface AccountRepository {
    fun getInfo(userId: Int): UserInfoDto
    fun changeInfo(userId: Int, surname: String, name: String, patronymic: String, photo: String?)
}