package repository.exercise

import dto.exercise.ExerciseDto
import dto.exercise.ExerciseTypeDto
import dto.exercise.GroupDto
import dto.exercise.MuscleDto

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
 * @see ExerciseRepositoryImpl
 */
interface ExerciseRepository {
    fun getAllMusclesForGroup(groupId: Int): List<MuscleDto>
    fun getAllGroups(): List<GroupDto>
    fun getAllExerciseTypes(): List<ExerciseTypeDto>
    fun addExercise(
        name: String,
        description: String?,
        video: String?,
        ownerUserId: Int,
        muscleId: Int?,
        exerciseTypeId: Int
    )

    fun getAllUserExercise(userId: Int): List<ExerciseDto>
    fun changeUserExercise(
        exerciseId: Long,
        name: String,
        description: String?,
        video: String?,
        ownerUserId: Int,
        muscleId: Int?,
        exerciseTypeId: Int
    )

    fun removeUserExercise(exerciseId: Long)
    fun getAllExerciseForExerciseType(exerciseTypeId: Int): List<ExerciseDto>
    fun getAllExerciseForMuscle(muscleId: Int): List<ExerciseDto>
    fun getAllExercise(): List<ExerciseDto>
    fun getAllExerciseByName(name: String): List<ExerciseDto>
}