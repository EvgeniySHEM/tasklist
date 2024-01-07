package ru.sanctio.tasklist.web.mappers;

import org.mapstruct.Mapper;
import ru.sanctio.tasklist.domain.user.User;
import ru.sanctio.tasklist.web.dto.user.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
