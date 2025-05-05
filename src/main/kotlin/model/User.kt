package model

import model.User.id
import model.User.mail
import model.User.name
import model.User.password
import model.User.patronymic
import model.User.photo
import model.User.surname
import org.jetbrains.exposed.sql.Table

/**
 * Таблица User (пользователь)
 *
 * @property id идентификатор (автоинкрементируемый)
 * @property mail почта
 * @property password пароль
 * @property surname фамилия
 * @property name имя
 * @property patronymic отчество
 * @property photo фото (может быть null)
 */
object User : Table("user") {
    val id = integer("id").autoIncrement()
    val mail = text("mail")
    val password = text("password")
    val surname = varchar("surname", 45)
    val name = varchar("name", 40)
    val patronymic = varchar("patronymic", 45)
    val photo = text("photo").nullable()

    override val primaryKey = PrimaryKey(id)
}