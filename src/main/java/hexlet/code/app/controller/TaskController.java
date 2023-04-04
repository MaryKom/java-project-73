package hexlet.code.app.controller;

import hexlet.code.app.dto.TaskDto;
import hexlet.code.app.model.Task;
import hexlet.code.app.repository.TaskRepository;
import hexlet.code.app.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hexlet.code.app.controller.TaskController.TASK_CONTROLLER_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("${base-url}" + TASK_CONTROLLER_PATH)
public class TaskController {
    public static final String TASK_CONTROLLER_PATH = "/tasks";
    public static final String ID = "/id";
    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private static final String TASK_OWNER =
        "@taskRepository.findById(#id).get().getAuthor().getEmail() == authentication.getName()";

    @Operation(summary = "Create new task")
    @ApiResponse(responseCode = "201", description = "Task created")
    @ResponseStatus(CREATED)
    @PostMapping
    public Task createTask(@RequestBody final TaskDto taskDto) {
        return  taskService.createNewTask(taskDto);
    }

    @ApiResponses(@ApiResponse(responseCode = "200", content =
        @Content(schema = @Schema(implementation = Task.class))))
    @Operation(summary = "Get all tasks")
    @GetMapping
    public List<Task> getAllTask() {
        return taskRepository.findAll()
                .stream().toList();
    }

    @ApiResponses(@ApiResponse(responseCode = "200"))
    @Operation(summary = "Get task")
    @GetMapping(ID)
    public Task getTaskById(@PathVariable final Long id) {
        return taskRepository.getById(id);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task update"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @Operation(summary = "Update task")
    @PreAuthorize(TASK_OWNER)
    @PutMapping(ID)
    public Task updateTaskById(@PathVariable final Long id, @RequestBody TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task delete"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @Operation(summary = "Delete task")
    @PreAuthorize(TASK_OWNER)
    @DeleteMapping(ID)
    public void deleteTaskById(@PathVariable final Long id) {
        taskRepository.deleteById(id);
    }
}
