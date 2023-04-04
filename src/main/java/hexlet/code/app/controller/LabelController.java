package hexlet.code.app.controller;

import hexlet.code.app.dto.LabelDto;
import hexlet.code.app.model.Label;
import hexlet.code.app.repository.LabelRepository;
import hexlet.code.app.service.LabelService;
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

import static hexlet.code.app.controller.LabelController.LABEL_CONTROLLER_PATH;
@RestController
@AllArgsConstructor
@RequestMapping("${base-url}" + LABEL_CONTROLLER_PATH)
        public class LabelController {
    public static final String LABEL_CONTROLLER_PATH = "/labels";
    public static final String ID = "/id";
    private final LabelService labelService;
    private final LabelRepository labelRepository;

    @PostMapping
    public Label createLabel(@RequestBody final LabelDto labelDto) {
        return labelService.createNewLabel(labelDto);
    }

    @GetMapping
    public List<Label> getAllLabel() {
        return labelRepository.findAll().stream().toList();
    }

    @GetMapping(ID)
    public Label getLabelById(@PathVariable final Long id) {
        return labelRepository.getById(id);
    }

    @PutMapping(ID)
    public Label updateLabelById(@PathVariable final Long id, @RequestBody final LabelDto labelDto) {
        return labelService.updateLabel(id, labelDto);
    }

    @DeleteMapping(ID)
    public void deleteLabelById(@PathVariable final Long id) {
        labelRepository.deleteById(id);
    }
}
