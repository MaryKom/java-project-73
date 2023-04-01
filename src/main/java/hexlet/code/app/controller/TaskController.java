package hexlet.code.app.controller;

import hexlet.code.app.dto.TaskDto;
import hexlet.code.app.model.Task;
import hexlet.code.app.repository.TaskRepository;
import hexlet.code.app.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hexlet.code.app.controller.TaskController.TASK_CONTROLLER_PATH;

@RestController
@AllArgsConstructor
@RequestMapping("${base-url}" + TASK_CONTROLLER_PATH)
public class TaskController {
    public static final String TASK_CONTROLLER_PATH = "/tasks";
    public static final String ID = "/id";
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @PostMapping
    public Task createTask(@RequestBody final TaskDto taskDto) {
        return  taskService.createNewTask(taskDto);
    }

    @GetMapping
    public List<Task> getAllTask() {
        return taskRepository.findAll()
                .stream().toList();
    }

    @GetMapping(ID)
    public Task getTaskById(@PathVariable final Long id) {
        return taskRepository.getById(id);
    }

    @PutMapping(ID)
    public Task updateTaskById(@PathVariable final Long id, @RequestBody TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }

    @DeleteMapping(ID)
    public void deleteTaskById(@PathVariable final Long id) {
        taskRepository.deleteById(id);
    }
}
