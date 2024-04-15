package vn.hoidanit.laptopshop.service;

import vn.hoidanit.laptopshop.domian.User;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handleHello() {
        return "hello from service";
    }

    public User handleSaveUser(User user) {
        user = this.userRepository.save(user);
        System.out.println(user);
        return user;
    }
}
