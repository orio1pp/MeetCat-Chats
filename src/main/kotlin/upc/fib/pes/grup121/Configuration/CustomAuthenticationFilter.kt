package upc.fib.pes.grup121.Configuration

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.streams.toList

@Component
class CustomAuthenticationFilter(
) : UsernamePasswordAuthenticationFilter(
) {
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        var username: String = request.getParameter("username");
        var password: String = request.getParameter("password");
        var usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authentication: Authentication
    ) {
        var user: User = authentication.principal as User
        var algorithm : Algorithm = Algorithm.HMAC256("secret".toByteArray()) //cambiar esto, la encriptacion habria que sacarla de algun sitio securizado.
        var access_token : String = JWT.create().withSubject(user.username).withExpiresAt(Date(System.currentTimeMillis() + 30*60*1000))
            .withIssuer(request?.requestURL.toString())
            .withClaim("roles", user.authorities.stream().map(GrantedAuthority::getAuthority).toList())
            .sign(algorithm)
        var refresh_token : String = JWT.create().withSubject(user.username).withExpiresAt(Date(System.currentTimeMillis() + 30*60*1000))
            .withIssuer(request?.requestURL.toString())
            .sign(algorithm)
        response.setHeader("accest_token", access_token);
        response.setHeader("refresh_token", refresh_token);
    }
}