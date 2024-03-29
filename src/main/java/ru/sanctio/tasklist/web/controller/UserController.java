package ru.sanctio.tasklist.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.sanctio.tasklist.domain.task.Task;
import ru.sanctio.tasklist.domain.user.User;
import ru.sanctio.tasklist.service.TaskService;
import ru.sanctio.tasklist.service.UserService;
import ru.sanctio.tasklist.web.dto.task.TaskDto;
import ru.sanctio.tasklist.web.dto.user.UserDto;
import ru.sanctio.tasklist.web.dto.validation.OnCreate;
import ru.sanctio.tasklist.web.dto.validation.OnUpdate;
import ru.sanctio.tasklist.web.mappers.TaskMapper;
import ru.sanctio.tasklist.web.mappers.UserMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;

    private final TaskService taskService;

    private final UserMapper userMapper;

    private final TaskMapper taskMapper;

    @PutMapping
    @Operation(summary = "Update user")
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        User updateUser = userService.update(user);
        return userMapper.toDto(updateUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get UserDto by id")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get all user tasks")
    public List<TaskDto> getTasksByUserId(@PathVariable Long id) {
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    @Operation(summary = "Add task to user")
    public TaskDto createTask(@PathVariable Long id,
                              @Validated(OnCreate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }
}
