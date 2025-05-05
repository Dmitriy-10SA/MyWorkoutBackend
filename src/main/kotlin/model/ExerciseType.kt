package model

import model.ExerciseType.id
import model.ExerciseType.name
import org.jetbrains.exposed.sql.Table

/**
 * Таблица ExerciseType (тип упражнения)
 *
 * @property id идентификатор
 * @property name название
 */
object ExerciseType : Table("exercise_type") {
    val id = integer("id")
    val name = varchar("name", 7)

    override val primaryKey = PrimaryKey(id)
}