package fun.fengwk.upms.core.model;

import fun.fengwk.convention4j.common.NullSafe;
import fun.fengwk.upms.share.model.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author fengwk
 */
@Data
public class UserBO {

    private String id;
    private String userSystemCode;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private Map<String, Object> info;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

    public static UserBO create(String userSystemCode, String id, UserCreateBO createBO) {
        UserBO userBO = build(userSystemCode, id, createBO);
        LocalDateTime now = LocalDateTime.now();
        userBO.setCreateTime(now);
        userBO.setModifiedTime(now);
        return userBO;
    }

    public static UserBO updateSelective(String userSystemCode, UserUpdateBO updateBO) {
        UserBO userBO = build(userSystemCode, updateBO.getId(), updateBO);
        LocalDateTime now = LocalDateTime.now();
        userBO.setModifiedTime(now);
        return userBO;
    }

    public static UserBO build(String userSystemCode, String userId, UserCreateBO createBO) {
        UserBO userBO = new UserBO();
        userBO.setUserSystemCode(userSystemCode);
        userBO.setId(userId);
        userBO.setUsername(createBO.getUsername());
        userBO.setEmail(createBO.getEmail());
        userBO.setMobile(createBO.getMobile());
        userBO.setPassword(createBO.getPassword());
        userBO.setInfo(NullSafe.of(createBO.getUserInfo()));
        return userBO;
    }

    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(getId());
        userDTO.setUsername(getUsername());
        userDTO.setEmail(getEmail());
        userDTO.setMobile(getMobile());
        userDTO.setPassword(getPassword());
        userDTO.setUserInfo(getInfo());
        userDTO.setCreateTime(getCreateTime());
        userDTO.setModifiedTime(getModifiedTime());
        return userDTO;
    }

}
