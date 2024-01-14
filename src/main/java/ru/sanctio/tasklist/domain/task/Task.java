package ru.sanctio.tasklist.domain.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class Task {

    private Long id;

    private String title;

    private String description;

    private Status status;

    private LocalDateTime expirationDate;
}
