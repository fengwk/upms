package fun.fengwk.upms.core.model;

import fun.fengwk.upms.share.model.UserCreateDTO;
import lombok.Data;

import java.util.Map;

/**
 * @author fengwk
 */
@Data
public class UserCreateBO {

    private String username;
    private String email;
    private String mobile;
    private String password;
    private Map<String, Object> userInfo;

    public static UserCreateBO fromDTO(UserCreateDTO createDTO) {
        if (createDTO == null) {
            return null;
        }
        UserCreateBO userCreateBO = new UserCreateBO();
        userCreateBO.setUsername(createDTO.getUsername());
        userCreateBO.setEmail(createDTO.getEmail());
        userCreateBO.setMobile(createDTO.getMobile());
        userCreateBO.setPassword(createDTO.getPassword());
        userCreateBO.setUserInfo(createDTO.getUserInfo());
        return userCreateBO;
    }

}

