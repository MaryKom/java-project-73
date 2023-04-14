package hexlet.code.app.controller;

import hexlet.code.app.dto.TaskStatusDto;
import hexlet.code.app.model.TaskStatus;
import hexlet.code.app.repository.TaskStatusRepository;
import hexlet.code.app.service.TaskStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static hexlet.code.app.controller.TaskStatusController.TASK_STATUS_CONTROLLER_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("${base-url}" + TASK_STATUS_CONTROLLER_PATH)
public class TaskStatusController {
    public static final String TASK_STATUS_CONTROLLER_PATH = "/statuses";
    public static final String ID = "/{id}";
    private final TaskStatusService taskStatusService;
    private final TaskStatusRepository taskStatusRepository;


    @Operation(summary = "Create new status")
    @ApiResponse(responseCode = "201", description = "Status created")
    @ResponseStatus(CREATED)
    @PostMapping
    public TaskStatus createTaskStatus(@RequestBody @Valid final TaskStatusDto taskStatusDto) {
        return taskStatusService.createNewTaskStatus(taskStatusDto);
    }

    @ApiResponses(@ApiResponse(responseCode = "200", content =
        @Content(schema = @Schema(implementation = TaskStatus.class))))
    @Operation(summary = "Get all statuses")
    @GetMapping
    public List<TaskStatus> getAllTaskStatus() {
        return taskStatusRepository.findAll()
                .stream().toList();
    }

    @ApiResponses(@ApiResponse(responseCode = "200"))
    @Operation(summary = "Get status")
    @GetMapping(ID)
    public TaskStatus getTaskStatusById(@PathVariable Long id) {
        return taskStatusRepository.getById(id);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task status update"),
        @ApiResponse(responseCode = "404", description = "Task status not found")
    })
    @Operation(summary = "Update status")
    @PutMapping(ID)
    public TaskStatus updateTaskStatusById(@PathVariable Long id, @RequestBody @Valid TaskStatusDto taskStatusDto) {
        return taskStatusService.updateTaskStatus(id, taskStatusDto);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task status delete"),
        @ApiResponse(responseCode = "404", description = "Task status not found")
    })
    @Operation(summary = "Delete status")
    @DeleteMapping(ID)
    public void deleteTaskStatusById(@PathVariable Long id) {
        taskStatusRepository.deleteById(id);
    }
}
