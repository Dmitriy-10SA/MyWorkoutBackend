package model

import model.Workout.date
import model.Workout.id
import model.Workout.name
import model.Workout.ownerUserId
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

/**
 * Таблица Workout (тренировка)
 *
 * @property id идентификатор (автоинкрементируемый)
 * @property name название
 * @property date дата
 * @property ownerUserId id пользователя-владельца
 */
object Workout : Table("workout") {
    val id = long("id").autoIncrement()
    val name = text("name").nullable()
    val date = date("date")
    val ownerUserId = integer("owner_user_id").references(
        ref = User.id,
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.CASCADE
    )

    override val primaryKey = PrimaryKey(id)
}