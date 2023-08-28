package fun.fengwk.upms.share.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

/**
 * @author fengwk
 */
@Data
public class UserUpdateDTO {

    @NotEmpty
    private String appId;
    @NotEmpty
    private String id;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private Map<String, Object> userInfo;

}

