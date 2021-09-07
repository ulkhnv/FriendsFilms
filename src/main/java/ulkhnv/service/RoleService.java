package ulkhnv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulkhnv.dao.RoleDAO;
import ulkhnv.model.Role;

import javax.transaction.Transactional;

@Service
public class RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Transactional
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }
}
