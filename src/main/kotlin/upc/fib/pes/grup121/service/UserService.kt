package upc.fib.pes.grup121.service

import org.springframework.stereotype.Service
import upc.fib.pes.grup121.model.User
import upc.fib.pes.grup121.repository.UserRepository

@Service
class UserService(private final var userRepository: UserRepository) {
    fun getUsers(): List<User> {
        return userRepository.findAll();
    }
    fun getUserByUsername(username:String): User{
        return userRepository.findUserByUseranme(username)
    }
    fun setUser(user: User) {
        userRepository.save(user);
    }
}