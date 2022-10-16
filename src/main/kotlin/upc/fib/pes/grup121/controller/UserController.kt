package upc.fib.pes.grup121.controller

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
    fun getUser(@RequestParam username: String): User {
        return userService.getUserByUsername(username)
    }

    @PostMapping("users/setUser")
    fun setUser(@RequestBody user: User) {
        userService.setUser(user);
    }
}