package web.servise;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) { userDao.add(user); }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public void changeNickname(Long id, String newNickname){ userDao.changeNickname(id, newNickname);}

    @Override
    public void changeEmail(Long id, String newEmail){ userDao.changeEmail(id, newEmail); }

    @Override
    public void changePassword(Long id, String newPassword){ userDao.changePassword(id, newPassword); }
}
