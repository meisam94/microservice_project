package ir.javapro.user.controller;

import ir.javapro.user.model.User;
import ir.javapro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/get-by-id/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/get-all")
    public List<User> findAll() {
        return userService.findAll();
    }

}
