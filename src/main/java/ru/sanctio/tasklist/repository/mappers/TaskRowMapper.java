package ru.sanctio.tasklist.repository.mappers;

import lombok.SneakyThrows;
import ru.sanctio.tasklist.domain.task.Status;
import ru.sanctio.tasklist.domain.task.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TaskRowMapper {

    //TODO подумать над единым классом мапинга

    @SneakyThrows
    public static Task mapRow(ResultSet resultSet) {
        if(resultSet.next()) {
            Task task = new Task();
            task.setId(resultSet.getLong("task_id"));
            extract(resultSet, task);
            return task;
        }
        return null;
    }

    private static void extract(ResultSet resultSet, Task task) throws SQLException {
        task.setTitle(resultSet.getString("task_title"));
        task.setDescription(resultSet.getString("task_description"));
        task.setStatus(Status.valueOf(resultSet.getString("task_status")));
        Timestamp timestamp = resultSet.getTimestamp("task_expiration_date");
        if(timestamp != null) {
            task.setExpirationDate(timestamp.toLocalDateTime());
        }
    }

    @SneakyThrows
    public static List<Task> mapRows(ResultSet resultSet) {
        List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            Task task = new Task();
            task.setId(resultSet.getLong("task_id"));
            if(!resultSet.wasNull()) {
                extract(resultSet, task);
                tasks.add(task);
            }
        }
        return tasks;
    }

}
