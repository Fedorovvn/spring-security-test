package web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.model.UserForm;
import web.servise.UserService;

import java.util.*;

@Controller
public class UserController {

    private final UserService userService;

    @Value("${error.message}")
    private String errorMessage;

    public UserController(UserService userService) {
        this.userService = userService;
        userService.add(new User("Karella", "or_009@mail.ru", "1", Collections.singleton(new Role("ROLE_ADMIN"))));
        userService.add(new User("YuIIOO-", "Jupitr@yandex.ru", "2", Set.of(new Role("ROLE_ADMIN"), new Role("ROLE_USER"))));
        userService.add(new User("Lotry=-", "toolong@gmail.com", "3", Collections.singleton(new Role("ROLE_USER"))));
        userService.add(new User("tea_for_one", "urban@gmail.com", "4", Collections.singleton(new Role("ROLE_USER"))));
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @RequestMapping(value = {"/admin/userlist"}, method = RequestMethod.GET)
    public String userList(Model model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    @RequestMapping(value = {"/admin/adduser"}, method = RequestMethod.GET)
    public String showAddUserPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userform", userForm);
        return "adduser";
    }

    @RequestMapping(value = {"/admin/adduser"}, method = RequestMethod.POST)
    public String saveUser(Model model, @ModelAttribute("userform") UserForm userForm) {

        String nickname = userForm.getNickname();
        String email = userForm.getEmail();
        String password = userForm.getPassword();

        if (nickname != null && nickname.length() > 0
                && email != null && email.length() > 0) {
            userService.add(new User(nickname, email, password, Collections.singleton(new Role("ROLE_USER"))));
            return "redirect:userlist";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "adduser";
    }

    @RequestMapping(value = {"/admin/edituser"}, method = RequestMethod.GET)
    public String showEditPage(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("userform", new UserForm());
        model.addAttribute("user", userService.getUser(id));
        return "edituser";
    }

    @RequestMapping(value = {"/admin/edit"}, method = RequestMethod.POST)
    public String editUser(@ModelAttribute("userform") UserForm editForm, @RequestParam(value = "id") Long id) {
        String newNickname = editForm.getNickname();
        String newEmail = editForm.getEmail();
        String newPassword = editForm.getPassword();

        if (newNickname != null && newNickname.length() > 0) {
            userService.changeNickname(id, newNickname);
        }
        if (newEmail != null && newEmail.length() > 0) {
            userService.changeEmail(id, newEmail);
        }
        if (newPassword != null && newPassword.length() > 0) {
            userService.changePassword(id, newPassword);
        }

        return "redirect:userlist";
    }


    @RequestMapping(value = {"/admin/delete"})
    public String delete(@RequestParam(value = "id") Long id) {
        System.out.println(id);
        userService.deleteUser(id);
        return "redirect:userlist";
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String showInfoPage(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", user);
        return "userinfo";
    }

}