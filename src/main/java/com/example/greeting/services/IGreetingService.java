package com.example.greeting.services;

import com.example.greeting.model.Greeting;
import com.example.greeting.model.User;

import java.util.List;

public interface IGreetingService {
    Greeting addGreeting(User user);
    Greeting getGreetingById(long id);

    String deleteGreetingById(long id);

    Greeting editGreetingByName(long id, User user);

    List<Greeting> viewGreetings();

    Greeting editGreeting(long id, User user);
}
