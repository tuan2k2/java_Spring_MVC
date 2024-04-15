package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import vn.hoidanit.laptopshop.domian.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("eric", "test");
        model.addAttribute("hoidanit", "From controller with model ");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserAdmin(Model model) {
        model.addAttribute("newUser", new User());
        return "./admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    @ResponseBody
    public String CreateUserPage(Model model, @ModelAttribute("newUser") User hoidanit) {
        System.out.println(hoidanit);
        this.userService.handleSaveUser(hoidanit);
        return "hello";
    }
}

// @RestController
// public class UserController {

// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("/")
// public String getHomePage() {
// return userService.handleHello();
// }

// }
