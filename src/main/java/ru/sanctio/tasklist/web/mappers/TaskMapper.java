package ru.sanctio.tasklist.web.mappers;

import org.mapstruct.Mapper;
import ru.sanctio.tasklist.domain.task.Task;
import ru.sanctio.tasklist.web.dto.task.TaskDto;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto> {
}
