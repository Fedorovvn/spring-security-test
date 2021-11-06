package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public User getUser(Long id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        User user = em.createQuery("SELECT u from User u WHERE u.nickname = :name", User.class).
                setParameter("name", name).getSingleResult();
        return user;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        em.remove(getUser(id));
    }


    @Override
    public List<User> listUsers() {
        return em.createQuery("from User").getResultList();
    }


    @Transactional
    @Override
    public void changeNickname(Long id, String newNickname) {
        System.out.println(id);
        getUser(id).setNickname(newNickname);
    }

    @Transactional
    @Override
    public void changeEmail(Long id, String newEmail) {
        System.out.println(id);
        getUser(id).setEmail(newEmail);
    }

    @Transactional
    @Override
    public void changePassword(Long id, String newPassword) {
        System.out.println(id);
        getUser(id).setPassword(newPassword);
    }
}
