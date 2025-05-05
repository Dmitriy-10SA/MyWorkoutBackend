package model

import model.Muscle.groupId
import model.Muscle.id
import model.Muscle.name
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

/**
 * Таблица Muscle (мышца)
 *
 * @property id идентификатор
 * @property groupId id мышечной группы
 * @property name название
 */
object Muscle : Table("muscle") {
    val id = integer("id")
    val groupId = integer("group_id").references(
        ref = Group.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val name = varchar("name", 10)

    override val primaryKey = PrimaryKey(id)
}