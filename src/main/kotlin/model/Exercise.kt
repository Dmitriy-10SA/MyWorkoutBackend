package model

import model.Exercise.description
import model.Exercise.exerciseTypeId
import model.Exercise.id
import model.Exercise.muscleId
import model.Exercise.name
import model.Exercise.ownerUserId
import model.Exercise.video
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

/**
 * Таблица Exercise (упражнение)
 *
 * @property id идентификатор (автоинкрементируемый)
 * @property name название
 * @property description описание
 * @property video видео
 * @property ownerUserId id пользователя
 * @property muscleId id мышцы
 * @property exerciseTypeId id типа упражнения
 */
object Exercise : Table("exercise") {
    val id = long("id").autoIncrement()
    val name = text("name")
    val description = text("description").nullable()
    val video = text("video").nullable()
    val ownerUserId = integer("owner_user_id").references(
        ref = User.id,
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.CASCADE
    )
    val muscleId = integer("muscle_id").references(
        ref = Muscle.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    ).nullable()
    val exerciseTypeId = integer("exercise_type_id").references(
        ref = ExerciseType.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )

    override val primaryKey = PrimaryKey(id)
}