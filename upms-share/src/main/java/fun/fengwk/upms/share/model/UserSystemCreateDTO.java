package fun.fengwk.upms.share.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author fengwk
 */
@Data
public class UserSystemCreateDTO {

    @NotEmpty
    private String code;
    @NotEmpty
    private String type;
    @NotEmpty
    private String name;
    private String description;

}
