package repository.auth

import dto.common.UserInfoDto
import model.User
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * @property registerUser регистрация пользователя
 * @property getUserInfoById получение пользователя по id
 * @property getUserInfoByMail получение пользователя по mail
 * @property getUserInfo получение информации о пользователе по условию
 * @property getUserInfoOrNull получение информации о пользователе по условию (возможен null)
 * @property mapToUserInfoDto перевод полученной строки из таблицы в UserInfoDto
 *
 * @see AuthRepository
 * @see UserInfoDto
 */
class AuthRepositoryImpl : AuthRepository {
    override fun registerUser(mail: String, password: String, surname: String, name: String, patronymic: String) {
        return transaction {
            User.insert {
                it[User.mail] = mail
                it[User.password] = password
                it[User.surname] = surname
                it[User.name] = name
                it[User.patronymic] = patronymic
            }
        }
    }

    override fun getUserInfoByMail(mail: String) = getUserInfo(User.mail eq mail)

    override fun getUserInfoOrNullByMail(mail: String) = getUserInfoOrNull(User.mail eq mail)

    override fun getUserInfoById(id: Int) = getUserInfo(User.id eq id)

    private fun getUserInfo(predicate: Op<Boolean>) = transaction {
        User.selectAll().where(predicate).single().mapToUserInfoDto()
    }

    private fun getUserInfoOrNull(predicate: Op<Boolean>) = transaction {
        User.selectAll().where(predicate).singleOrNull()?.mapToUserInfoDto()
    }

    private fun ResultRow.mapToUserInfoDto() = UserInfoDto(
        id = this[User.id],
        mail = this[User.mail],
        password = this[User.password],
        surname = this[User.surname],
        name = this[User.name],
        patronymic = this[User.patronymic],
        photo = this[User.photo]
    )
}