package hexlet.code.app.service;

import hexlet.code.app.dto.TaskDto;
import hexlet.code.app.model.Task;
import hexlet.code.app.model.TaskStatus;
import hexlet.code.app.model.User;
import hexlet.code.app.repository.TaskRepository;
import hexlet.code.app.repository.TaskStatusRepository;
import hexlet.code.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Task createNewTask(final TaskDto taskDto) {
        final Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        final User author = userService.getCurrentUser();
        task.setAuthor(author);
        if (taskDto.getExecutorId() != null) {
            final User executor = userRepository.findById(taskDto.getExecutorId())
                    .orElseThrow(() -> new NoSuchElementException("Executor not found"));
            task.setExecutor(executor);
        }
        final TaskStatus taskStatus = taskStatusRepository.findById(taskDto.getTaskStatusId())
                .orElseThrow(() -> new NoSuchElementException("TaskStatus not found"));
        task.setTaskStatus(taskStatus);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(final Long id, final TaskDto taskDto) {
        final Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        final User author = userService.getCurrentUser();
        task.setAuthor(author);
        if (taskDto.getExecutorId() != null) {
            final User executor = userRepository.findById(taskDto.getExecutorId())
                    .orElseThrow(() -> new NoSuchElementException("Executor not found"));
            task.setExecutor(executor);
        }
        final TaskStatus taskStatus = taskStatusRepository.findById(taskDto.getTaskStatusId())
                .orElseThrow(() -> new NoSuchElementException("TaskStatus not found"));
        task.setTaskStatus(taskStatus);
        return taskRepository.save(task);
    }
}
