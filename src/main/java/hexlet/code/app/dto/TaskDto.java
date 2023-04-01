package hexlet.code.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @NotBlank
    @Size(min = 1)
    private String name;

    private String description;

    @NotBlank
    private Long taskStatusId;

    @NotBlank
    private Long authorId;

    private Long executorId;

}
