package service

import repository.account.AccountRepository

/**
 * Сервис для получения инф-ии об аккаунте и изменения данных аккаунта
 *
 * @property getInfo получение информации о пользователе
 * @property changeInfo изменение информации о пользователе
 *
 * @see AccountRepository
 */
class AccountService(
    private val accountRepository: AccountRepository
) {
    fun getInfo(userId: Int) = accountRepository.getInfo(userId)

    fun changeInfo(userId: Int, surname: String, name: String, patronymic: String, photo: String?) {
        accountRepository.changeInfo(userId, surname, name, patronymic, photo)
    }
}