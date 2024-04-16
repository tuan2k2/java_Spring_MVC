package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUser = this.userService.getAllUsersByEmail("tuan@gmail.com");
        System.out.println(arrUser);
        model.addAttribute("eric", "test");
        model.addAttribute("hoidanit", "From controller with model ");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getAllUserAdmin(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("user", users);
        return "./admin/user/table-user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getDetailUser(Model model, @PathVariable Long id) {
        System.out.println("check path : " + id);
        User user = this.userService.getDetailUserById(id);
        model.addAttribute("user", user);
        return "./admin/user/UserDetail";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String updatelUser(Model model, @PathVariable Long id) {
        User user = this.userService.getDetailUserById(id);
        model.addAttribute("newUser", user);
        return "./admin/user/updateUser";
    }

    @RequestMapping("/admin/user/create")
    public String getUserAdmin(Model model) {
        model.addAttribute("newUser", new User());
        return "./admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String CreateUserPage(Model model, @ModelAttribute("newUser") User hoidanit) {
        System.out.println(hoidanit);
        this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(@ModelAttribute("newUser") User hoidanit) {
        User user = this.userService.getDetailUserById(hoidanit.getId());
        if (user != null) {
            user.setAddress(hoidanit.getAddress());
            user.setFullName(hoidanit.getFullName());
            user.setPhone(hoidanit.getPhone());
            this.userService.handleSaveUser(user);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUser(Model model, @PathVariable Long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "./admin/user/DeleteUser";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(@ModelAttribute("newUser") User hoidanit) {
        this.userService.DeleteUserById(hoidanit.getId());
        return "redirect:/admin/user";
    }

}
