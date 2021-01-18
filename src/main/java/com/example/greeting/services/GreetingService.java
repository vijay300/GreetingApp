package com.example.greeting.services;

import com.example.greeting.model.Greeting;
import com.example.greeting.model.User;
import com.example.greeting.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService implements IGreetingService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingRepository greetingRepository;

    @Override
    public Greeting addGreeting(User user) {
        String message = String.format(template, (user.toString().isEmpty()) ? "Hello World" : user.toString());
        return greetingRepository.save(new Greeting(counter.incrementAndGet(), message));
    }

    @Override
    public Greeting getGreetingById(long id) {
        return greetingRepository.findById(id).get();
    }

    public String deleteGreetingById(long id) {
        greetingRepository.deleteById(id);
        return "Greeting Deleted for Id: " + id;
    }

    @Override
    public Greeting editGreetingByName(long id, User user) {
        return greetingRepository.findById(id)
                                 .map(greeting -> {
                                     greeting.setMessage(String.format(template, (user.toString().isEmpty()) ? "Hello World" : user.toString()));
                              return greetingRepository.save(greeting);
                          })
                            .orElseGet(() -> {
                                String message = String.format(template, (user.toString().isEmpty()) ? "Hello World" : user.toString());
                                return greetingRepository.save(new Greeting(counter.incrementAndGet(), message));
        });
    }

    @Override
    public List<Greeting> viewGreetings() {
        return greetingRepository.findAll();
    }
}
