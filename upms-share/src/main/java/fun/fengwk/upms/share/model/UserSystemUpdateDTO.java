package fun.fengwk.upms.share.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author fengwk
 */
@Data
public class UserSystemUpdateDTO {

    @NotEmpty
    private String code;
    private String type;
    private String name;
    private String description;

}
