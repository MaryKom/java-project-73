package hexlet.code.app.controller;

import hexlet.code.app.dto.LabelDto;
import hexlet.code.app.model.Label;
import hexlet.code.app.repository.LabelRepository;
import hexlet.code.app.service.LabelService;
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

import static hexlet.code.app.controller.LabelController.LABEL_CONTROLLER_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("${base-url}" + LABEL_CONTROLLER_PATH)
public class LabelController {
    public static final String LABEL_CONTROLLER_PATH = "/labels";
    public static final String ID = "/id";
    private final LabelService labelService;
    private final LabelRepository labelRepository;

    @Operation(summary = "Create new label")
    @ApiResponse(responseCode = "201", description = "Label created")
    @ResponseStatus(CREATED)
    @PostMapping
    public Label createLabel(@RequestBody @Valid final LabelDto labelDto) {
        return labelService.createNewLabel(labelDto);
    }

    @ApiResponses(@ApiResponse(responseCode = "200", content =
        @Content(schema = @Schema(implementation = Label.class))))
    @Operation(summary = "Get all labels")
    @GetMapping
    public List<Label> getAllLabel() {
        return labelRepository.findAll().stream().toList();
    }

    @ApiResponses(@ApiResponse(responseCode = "200"))
    @Operation(summary = "Get label")
    @GetMapping(ID)
    public Label getLabelById(@PathVariable final Long id) {
        return labelRepository.getById(id);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Label update"),
        @ApiResponse(responseCode = "404", description = "Label not found")
    })
    @Operation(summary = "Update label")
    @PutMapping(ID)
    public Label updateLabelById(@PathVariable final Long id, @RequestBody final LabelDto labelDto) {
        return labelService.updateLabel(id, labelDto);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Label delete"),
        @ApiResponse(responseCode = "404", description = "Label not found")
    })
    @Operation(summary = "Delete label")
    @DeleteMapping(ID)
    public void deleteLabelById(@PathVariable final Long id) {
        labelRepository.deleteById(id);
    }
}
