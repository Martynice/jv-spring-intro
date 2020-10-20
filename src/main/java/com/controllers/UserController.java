package com.controllers;

import com.dto.UserResponseDto;
import com.model.User;
import com.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public void inject() {
        User user1 = new User();
        user1.setEmail("andrew@email.com");
        user1.setPassword("psw");
        userService.add(user1);

        User user2 = new User();
        user2.setEmail("dana@email.com");
        user2.setPassword("psw");
        userService.add(user2);

        User user3 = new User();
        user3.setEmail("bob@email.com");
        user3.setPassword("psw");
        userService.add(user3);

        User user4 = new User();
        user4.setEmail("alice@email.com");
        user4.setPassword("psw");
        userService.add(user4);
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return mapUser(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers()
                .stream()
                .map(this::mapUser)
                .collect(Collectors.toList());
    }

    private UserResponseDto mapUser(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
