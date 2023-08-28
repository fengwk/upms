package fun.fengwk.upms.core.model;

import fun.fengwk.upms.share.constant.UpmsErrorCodes;
import fun.fengwk.upms.share.model.UserSystemDTO;
import fun.fengwk.upms.share.model.UserSystemCreateDTO;
import fun.fengwk.upms.share.model.UserSystemUpdateDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * @author fengwk
 */
@Slf4j
@Data
public class UserSystemBO {

    private static final Pattern PATTERN_CODE = Pattern.compile("^[a-zA-Z0-9_]{1,16}$");

    private String code;
    private String type;
    private String name;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

    public static UserSystemBO create(UserSystemCreateDTO createDTO) {
        if (!PATTERN_CODE.matcher(createDTO.getCode()).matches()) {
            log.warn("Invalid user system code, userSystemCode: {}", createDTO.getCode());
            throw UpmsErrorCodes.INVALID_USER_SYSTEM_CODE.asThrowable();
        }
        UserSystemBO userSystemBO = build(
            createDTO.getCode(), createDTO.getType(), createDTO.getName(), createDTO.getDescription());
        LocalDateTime now = LocalDateTime.now();
        userSystemBO.setCreateTime(now);
        userSystemBO.setModifiedTime(now);
        return userSystemBO;
    }

    public static UserSystemBO updateSelective(UserSystemUpdateDTO updateDTO) {
        UserSystemBO selectiveBO = build(
            updateDTO.getCode(), updateDTO.getType(), updateDTO.getName(), updateDTO.getDescription());
        LocalDateTime now = LocalDateTime.now();
        selectiveBO.setModifiedTime(now);
        return selectiveBO;
    }

    private static UserSystemBO build(String code, String type, String name, String description) {
        UserSystemBO userSystemBO = new UserSystemBO();
        userSystemBO.setCode(code);
        userSystemBO.setType(type);
        userSystemBO.setName(name);
        userSystemBO.setDescription(description);
        return userSystemBO;
    }

    public UserSystemDTO toDTO() {
        UserSystemDTO userSystemDTO = new UserSystemDTO();
        userSystemDTO.setCode(getCode());
        userSystemDTO.setName(getName());
        userSystemDTO.setDescription(getDescription());
        userSystemDTO.setCreateTime(getCreateTime());
        userSystemDTO.setModifiedTime(getModifiedTime());
        return userSystemDTO;
    }

}
