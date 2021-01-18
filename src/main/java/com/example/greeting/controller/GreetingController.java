package com.example.greeting.controller;

import com.example.greeting.model.Greeting;
import com.example.greeting.model.User;
import com.example.greeting.services.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private IGreetingService greetingService;

/*    @GetMapping(value = {"/", "/home"})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        User user = new User();
        user.setFirstName(name);
        return greetingService.addGreeting(user);
    }*/

    @GetMapping("")
    public List<Greeting> greeting() {
        return greetingService.viewGreetings();
    }

    @PostMapping(value = {""})
    public Greeting postGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        User user = new User();
        user.setFirstName(name);
        return greetingService.addGreeting(user);
    }

    @GetMapping(value = {"/{id}"})
    public Greeting greeting(@PathVariable long id) {
        return greetingService.getGreetingById(id);
    }

    @DeleteMapping(value = {"/{id}"})
    public String greetingDelete(@PathVariable long id) {
        return greetingService.deleteGreetingById(id);
    }

    @PutMapping(value = "")
    public Greeting editGreeting(@RequestBody Greeting greeting) {
        long id = greeting.getId();
        User user = new User();
        user.setFirstName(greeting.getMessage());
        return greetingService.editGreeting(id, user);
    }

    @GetMapping(value = {"put/{id}", "put/{id}/{name}"})
    public Greeting putGreeting(@PathVariable long id,
                                @PathVariable String name) {
        User user = new User();
        user.setFirstName(name);
        return greetingService.editGreetingByName(id, user);
    }
}
