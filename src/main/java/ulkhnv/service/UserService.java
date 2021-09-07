package ulkhnv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ulkhnv.dao.UserDAO;
import ulkhnv.model.Role;
import ulkhnv.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public boolean addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.addUser(user);
        return true;
    }

    @Transactional
    public User getUserById(Long id) {
        try {
            return userDAO.getUserById(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public boolean updateUser(User user) {
        try {
            boolean isBCryptPassword = user.getPassword().length() < 45;
            if (isBCryptPassword) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userDAO.updateUser(user);
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void removeUser(Long id) {
        try {
            userDAO.removeUser(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public User getUserByUsername(String username) {
        try {
            return userDAO.getUserByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public boolean isExists(String username) {
        return userDAO.isExists(username);
    }
}
