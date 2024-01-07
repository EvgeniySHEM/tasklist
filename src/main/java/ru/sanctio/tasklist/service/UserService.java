package ru.sanctio.tasklist.service;

import ru.sanctio.tasklist.domain.user.User;

public interface UserService {

    User getById(Long id);

    User getUserByUsername(String username);

    User update(User user);

    User create(User user);

    boolean isTaskOwner(Long userId, Long taskId);

    void delete(Long id);

}
