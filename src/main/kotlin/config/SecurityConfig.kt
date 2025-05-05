package config

import at.favre.lib.crypto.bcrypt.BCrypt
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureSecurity() {
    install(Authentication) {
        jwt(MyWorkoutSecurity.USER_AUTH_JWT) {
            verifier(
                JWT
                    .require(MyWorkoutSecurity.ALGORITHM)
                    .withAudience(MyWorkoutSecurity.USER_AUDIENCE)
                    .withIssuer(MyWorkoutSecurity.ISSUER)
                    .build()
            )
            validate { jwtCredential ->
                when (MyWorkoutSecurity.isValidJwtCredential(jwtCredential)) {
                    true -> JWTPrincipal(jwtCredential.payload)
                    false -> null
                }
            }
            challenge { _, _ -> call.respond(HttpStatusCode.Unauthorized, "Invalid token") }
        }
    }
}

object MyWorkoutSecurity {
    const val USER_AUTH_JWT = "user-auth-jwt"

    const val ISSUER = "ktor-app-myworkoutbackend"

    const val USER_AUDIENCE = "user-audience"

    const val ID_CLAIM = "id-claim"

    private const val JWT_SECRET = "J241W315T-S24ECRE34T"
    val ALGORITHM = Algorithm.HMAC256(JWT_SECRET)

    fun isValidJwtCredential(jwtCredential: JWTCredential) = jwtCredential.payload.getClaim(ID_CLAIM) != null
}

object PasswordHasher {
    fun hash(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray())
    }

    fun verify(password: String, hashedPassword: String): Boolean {
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified
    }
}