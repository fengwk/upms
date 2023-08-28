package fun.fengwk.upms.share.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author fengwk
 */
@Data
public class UserDTO {

    private String userId;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private Map<String, Object> userInfo;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

}
