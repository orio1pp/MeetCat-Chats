package upc.fib.pes.grup121.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import upc.fib.pes.grup121.model.User
import upc.fib.pes.grup121.service.UserService

@Controller
class UserController(private final var userService: UserService) {
    @GetMapping("user/getUsers")
    fun getUsers(): List<User> {
        return userService.getUsers();
    }

    @GetMapping("users/getUserByUsername")
    fun getUser(@RequestParam username: String): ResponseEntity<User> {
        username.let {
            var user : User = userService.getUserByUsername(it)
            user.let {
                return ResponseEntity.ok(it)
            }
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("users/setUser")
    fun setUser(@RequestBody user: User) {
        userService.setUser(user);
    }
}