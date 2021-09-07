package ulkhnv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ulkhnv.dao.UserDAO;
import ulkhnv.model.User;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Метод находит сущность пользователя на основе имени пользователя
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User currentUser = userDAO.getUserByUsername(userName);
        if (currentUser == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        } else {
            return currentUser;
        }
    }
}