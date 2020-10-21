package com;

import com.config.AppConfig;
import com.model.User;
import com.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setEmail("andrew@email.com");
        user1.setPassword("psw");

        User user2 = new User();
        user2.setEmail("dana@email.com");
        user2.setPassword("psw");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.add(user1);
        userService.add(user2);
        userService.listUsers().forEach(System.out::println);
    }
}
