package model

import model.Group.id
import model.Group.name
import org.jetbrains.exposed.sql.Table

/**
 * Таблица Group (мышечная группа)
 *
 * @property id идентификатор
 * @property name название
 */
object Group : Table("group") {
    val id = integer("id")
    val name = varchar("name", 4)

    override val primaryKey = PrimaryKey(id)
}