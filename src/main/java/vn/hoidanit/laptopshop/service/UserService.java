package vn.hoidanit.laptopshop.service;

import vn.hoidanit.laptopshop.domian.User;

import java.util.List;

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

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User getDetailUserById(long id) {
        return this.userRepository.findById(id);
    }

    public User handleSaveUser(User user) {
        user = this.userRepository.save(user);
        System.out.println(user);
        return user;
    }

}
