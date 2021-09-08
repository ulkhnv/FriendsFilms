package ulkhnv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ulkhnv.model.User;
import ulkhnv.service.FilmService;
import ulkhnv.service.RoleService;
import ulkhnv.service.UserService;
import java.security.Principal;


@Controller
public class PageController {

    private UserService userService;
    private FilmService filmService;
    private RoleService roleService;
    @Autowired
    public PageController(UserService userService, FilmService filmService, RoleService roleService) {
        this.userService = userService;
        this.filmService = filmService;
        this.roleService = roleService;
    }

    @GetMapping("/home")
    public String homePage(ModelMap modelMap, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        modelMap.addAttribute("films", filmService.getAllFilms(user));
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("message") String message, @ModelAttribute("warning") String warning, @ModelAttribute("error") String error) {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @GetMapping("/admin/moderation")
    public String moderationPage(ModelMap modelMap) {
        modelMap.addAttribute("films",filmService.getNotModeratedFilms());
        return "moderation";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user,Model model) {
        if (userService.isExists(user.getUsername())) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        user.setRole(roleService.getRoleByName("USER"));
        userService.addUser(user);
        return "login";
    }
}
