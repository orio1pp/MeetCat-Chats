package upc.fib.pes.grup121.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import upc.fib.pes.grup121.model.User
import upc.fib.pes.grup121.service.UserService

@RestController
class UserController(private final var userService: UserService) {
    @GetMapping("user/getUsers")
    fun getUsers(): List<User>{
        return userService.getUsers();
    }

    @PostMapping("users/setUser")
    fun setUser(@RequestBody user: User) {
        userService.setUser(user);
    }
}