package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    User getUser(Long id);

    User getUserByName(String name);

    void deleteUser(Long id);

    List<User> listUsers();

    void changeNickname(Long id, String newNickname);

    void changeEmail(Long id, String newEmail);

    void changePassword(Long id, String newPassword);
}
