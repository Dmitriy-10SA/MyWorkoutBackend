package repository.exercise

import dto.exercise.ExerciseDto
import dto.exercise.ExerciseTypeDto
import dto.exercise.GroupDto
import dto.exercise.MuscleDto
import model.Exercise
import model.ExerciseType
import model.Group
import model.Muscle
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * @property getAllMusclesForGroup получение списка мышц по мышечной группе
 * @property getAllGroups получение списка групп
 * @property getAllExerciseTypes получение списка типов упражнений
 * @property addExercise добавление упражнения пользователя
 * @property getAllUserExercise получение всех упражнений, созданных пользователем по id
 * @property changeUserExercise изменение упражнения пользователя
 * @property removeUserExercise удаление упражнения пользователя
 * @property getAllExerciseForExerciseType получение списка упражнений для конкретного типа упражнения
 * @property getAllExerciseForMuscle получение списка упражнений для конкретной мышцы
 * @property getAllExercise получение списка всех упражнений
 * @property getAllExerciseByName получение списка всех упражнений, подходящих (или похожих) по названию
 *
 * @see ExerciseRepository
 */
class ExerciseRepositoryImpl : ExerciseRepository {
    override fun addExercise(
        name: String,
        description: String?,
        video: String?,
        ownerUserId: Int,
        muscleId: Int?,
        exerciseTypeId: Int
    ) {
        transaction {
            Exercise.insert {
                it[Exercise.name] = name
                it[Exercise.description] = description
                it[Exercise.video] = video
                it[Exercise.ownerUserId] = ownerUserId
                it[Exercise.muscleId] = muscleId
                it[Exercise.exerciseTypeId] = exerciseTypeId
            }
        }
    }

    override fun getAllMusclesForGroup(groupId: Int) = transaction {
        Muscle.selectAll().where(Muscle.groupId eq groupId).map {
            MuscleDto(
                id = it[Muscle.id],
                groupId = it[Muscle.groupId],
                name = it[Muscle.name]
            )
        }
    }

    override fun getAllGroups() = transaction {
        Group.selectAll().map {
            GroupDto(
                id = it[Group.id],
                name = it[Group.name]
            )
        }
    }

    override fun getAllExerciseTypes() = transaction {
        ExerciseType.selectAll().map {
            ExerciseTypeDto(
                id = it[ExerciseType.id],
                name = it[ExerciseType.name]
            )
        }
    }

    override fun getAllUserExercise(userId: Int) = transaction {
        Exercise.selectAll().where(Exercise.ownerUserId eq userId).mapToListExerciseDto()
    }

    override fun changeUserExercise(
        exerciseId: Long,
        name: String,
        description: String?,
        video: String?,
        ownerUserId: Int,
        muscleId: Int?,
        exerciseTypeId: Int
    ) {
        transaction {
            Exercise.update({ Exercise.id eq exerciseId }) {
                it[Exercise.name] = name
                it[Exercise.description] = description
                it[Exercise.video] = video
                it[Exercise.ownerUserId] = ownerUserId
                it[Exercise.muscleId] = muscleId
                it[Exercise.exerciseTypeId] = exerciseTypeId
            }
        }
    }

    override fun removeUserExercise(exerciseId: Long) {
        transaction {
            Exercise.deleteWhere { Exercise.id eq exerciseId }
        }
    }

    override fun getAllExerciseForExerciseType(exerciseTypeId: Int) = transaction {
        Exercise.selectAll().where(Exercise.exerciseTypeId eq exerciseTypeId).mapToListExerciseDto()
    }

    override fun getAllExerciseForMuscle(muscleId: Int) = transaction {
        Exercise.selectAll().where(Exercise.muscleId eq muscleId).mapToListExerciseDto()
    }

    override fun getAllExercise() = transaction {
        Exercise.selectAll().mapToListExerciseDto()
    }

    override fun getAllExerciseByName(name: String) = transaction {
        Exercise.selectAll().where(
            Exercise.name.lowerCase().like("%${name.lowercase()}%")
        ).mapToListExerciseDto()
    }

    private fun Query.mapToListExerciseDto() = this.map {
        ExerciseDto(
            id = it[Exercise.id],
            name = it[Exercise.name],
            description = it[Exercise.description],
            video = it[Exercise.video],
            ownerUserId = it[Exercise.ownerUserId],
            muscleId = it[Exercise.muscleId],
            exerciseTypeId = it[Exercise.exerciseTypeId]
        )
    }
}