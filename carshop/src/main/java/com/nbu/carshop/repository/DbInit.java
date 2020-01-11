package com.nbu.carshop.repository;

import com.nbu.carshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public DbInit() {
    }

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.deleteAll();

        User client = new User("client", encoder.encode("clientPass"), "CLIENT");
        User employee = new User("employee", encoder.encode("employeePass"), "EMPLOYEE");
        User admin = new User("admin", encoder.encode("adminPass"), "ADMIN");

        List<User> users = Arrays.asList(client, employee, admin);

        // Save to db
        this.userRepository.saveAll(users);
    }
}
