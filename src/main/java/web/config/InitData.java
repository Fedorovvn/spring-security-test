package web.config;

import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.servise.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Set;

@Component
public class InitData  {

    private final UserService userService;

    public InitData(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void load() {
        userService.add(new User("Karella", "or_009@mail.ru", "0000", Collections.singleton(new Role("ROLE_ADMIN"))));
        userService.add(new User("YuIIOO-", "Jupitr@yandex.ru", "0000", Set.of(new Role("ROLE_ADMIN"), new Role("ROLE_USER"))));
        userService.add(new User("Lotry=-", "toolong@gmail.com", "0000", Collections.singleton(new Role("ROLE_USER"))));
        userService.add(new User("tea_for_one", "urban@gmail.com", "0000", Collections.singleton(new Role("ROLE_USER"))));
    }

}