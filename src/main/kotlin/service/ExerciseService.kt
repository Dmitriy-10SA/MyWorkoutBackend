package service

import dto.exercise.ExerciseAddRequestDto
import dto.exercise.ExerciseDto
import repository.exercise.ExerciseRepository

/**
 * Сервис для действий над упражнениями и дополнительных дейсвий, связанных с ними
 *
 * @property getAllMusclesForGroup получение списка мышц по мышечной группе
 * @property getAllGroups получение списка групп
 * @property getAllExerciseTypes получение списка типов упражнений
 * @property addExercise добавление упражнения
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
class ExerciseService(
    private val exerciseRepository: ExerciseRepository
) {
    fun getAllMusclesForGroup(groupId: Int) = exerciseRepository.getAllMusclesForGroup(groupId)
    fun getAllGroups() = exerciseRepository.getAllGroups()
    fun getAllExerciseTypes() = exerciseRepository.getAllExerciseTypes()
    fun addExercise(exerciseAddRequestDto: ExerciseAddRequestDto, userId: Int) = exerciseRepository.addExercise(
        name = exerciseAddRequestDto.name,
        description = exerciseAddRequestDto.description,
        video = exerciseAddRequestDto.video,
        ownerUserId = userId,
        muscleId = exerciseAddRequestDto.muscleId,
        exerciseTypeId = exerciseAddRequestDto.exerciseTypeId
    )

    fun getAllUserExercise(userId: Int) = exerciseRepository.getAllUserExercise(userId)
    fun changeUserExercise(exerciseDto: ExerciseDto, userId: Int) = exerciseRepository.changeUserExercise(
        exerciseDto.id,
        exerciseDto.name,
        exerciseDto.description,
        exerciseDto.video,
        userId,
        exerciseDto.muscleId,
        exerciseDto.exerciseTypeId
    )

    fun removeUserExercise(exerciseId: Long) = exerciseRepository.removeUserExercise(exerciseId)
    fun getAllExerciseForExerciseType(
        exerciseTypeId: Int
    ) = exerciseRepository.getAllExerciseForExerciseType(exerciseTypeId)

    fun getAllExerciseForMuscle(muscleId: Int) = exerciseRepository.getAllExerciseForMuscle(muscleId)
    fun getAllExercise() = exerciseRepository.getAllExercise()
    fun getAllExerciseByName(name: String) = exerciseRepository.getAllExerciseByName(name)
}