package service

import com.auth0.jwt.JWT
import config.MyWorkoutSecurity
import config.PasswordHasher
import dto.auth.AuthResponseDto
import dto.auth.LoginRequestDto
import dto.auth.RegisterRequestDto
import repository.auth.AuthRepository

/**
 * Сервис для регистрации, проверки токена и входа
 *
 * @property login вход
 * @property register регистрация
 *
 * @see AuthRepository
 * @see LoginRequestDto
 * @see RegisterRequestDto
 * @see AuthResponseDto
 */
class AuthService(
    private val authRepository: AuthRepository
) {
    //вход
    fun login(loginRequestDto: LoginRequestDto): AuthResponseDto? {
        val mail = loginRequestDto.mail
        val password = loginRequestDto.password
        val user = authRepository.getUserInfoOrNullByMail(mail)
        return if (user != null && PasswordHasher.verify(password, user.password)) {
            AuthResponseDto(
                token = JWT
                    .create()
                    .withAudience(MyWorkoutSecurity.USER_AUDIENCE)
                    .withIssuer(MyWorkoutSecurity.ISSUER)
                    .withClaim(MyWorkoutSecurity.ID_CLAIM, authRepository.getUserInfoByMail(mail).id)
                    .sign(MyWorkoutSecurity.ALGORITHM)
            )
        } else {
            null
        }
    }

    //регистрация
    fun register(registerRequestDto: RegisterRequestDto): AuthResponseDto? {
        val mail = registerRequestDto.mail
        val password = registerRequestDto.password
        val surname = registerRequestDto.surname
        val name = registerRequestDto.name
        val patronymic = registerRequestDto.patronymic
        val user = authRepository.getUserInfoOrNullByMail(mail)
        if (user != null) return null
        authRepository.registerUser(mail, PasswordHasher.hash(password), surname, name, patronymic)
        val newUser = authRepository.getUserInfoOrNullByMail(mail)
        return if (newUser != null) {
            AuthResponseDto(
                token = JWT
                    .create()
                    .withAudience(MyWorkoutSecurity.USER_AUDIENCE)
                    .withIssuer(MyWorkoutSecurity.ISSUER)
                    .withClaim(MyWorkoutSecurity.ID_CLAIM, authRepository.getUserInfoByMail(mail).id)
                    .sign(MyWorkoutSecurity.ALGORITHM)
            )
        } else {
            null
        }
    }
}