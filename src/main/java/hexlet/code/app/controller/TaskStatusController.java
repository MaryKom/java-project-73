package hexlet.code.app.controller;

import hexlet.code.app.dto.TaskStatusDto;
import hexlet.code.app.model.TaskStatus;
import hexlet.code.app.repository.TaskStatusRepository;
import hexlet.code.app.service.TaskStatusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static hexlet.code.app.controller.TaskStatusController.TASK_STATUS_CONTROLLER_PATH;

@RestController
@AllArgsConstructor
@RequestMapping("${base-url}" + TASK_STATUS_CONTROLLER_PATH)
public class TaskStatusController {
    public static final String TASK_STATUS_CONTROLLER_PATH = "/statuses";
    public static final String ID = "/id";
    private final TaskStatusService taskStatusService;
    private final TaskStatusRepository taskStatusRepository;

    @PostMapping
    public TaskStatus createTaskStatus(@RequestBody @Valid final TaskStatusDto taskStatusDto) {
        return taskStatusService.createNewTaskStatus(taskStatusDto);
    }

    @GetMapping
    public List<TaskStatus> getAllTaskStatus() {
        return taskStatusRepository.findAll()
                .stream().toList();
    }

    @GetMapping(ID)
    public TaskStatus getTaskStatusById(@PathVariable Long id) {
        return taskStatusRepository.getById(id);
    }

    @PutMapping(ID)
    public TaskStatus updateTaskStatusById(@PathVariable Long id, @RequestBody @Valid TaskStatusDto taskStatusDto) {
        return taskStatusService.updateTaskStatus(id, taskStatusDto);
    }

    @DeleteMapping(ID)
    public void deleteTaskStatusById(@PathVariable Long id) {
        taskStatusRepository.deleteById(id);
    }
}
