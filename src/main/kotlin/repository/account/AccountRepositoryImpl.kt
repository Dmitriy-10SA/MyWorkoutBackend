package repository.account

import dto.common.UserInfoDto
import model.User
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

/**
 * @property getInfo получение информации о пользователе
 * @property changeInfo изменение информации о пользователе
 *
 * @see AccountRepository
 */
class AccountRepositoryImpl : AccountRepository {
    override fun getInfo(userId: Int) = transaction {
        User.selectAll().where(User.id eq userId).single().let {
            UserInfoDto(
                id = it[User.id],
                mail = it[User.mail],
                password = it[User.password],
                surname = it[User.surname],
                name = it[User.name],
                patronymic = it[User.patronymic],
                photo = it[User.photo]
            )
        }
    }

    override fun changeInfo(userId: Int, surname: String, name: String, patronymic: String, photo: String?) {
        transaction {
            User.update({ User.id eq userId }) {
                it[User.name] = name
                it[User.surname] = surname
                it[User.patronymic] = patronymic
                it[User.photo] = photo
            }
        }
    }
}