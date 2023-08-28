package fun.fengwk.upms.core.model;

import fun.fengwk.upms.share.model.UserSystemTypeDTO;
import lombok.Data;

/**
 * 用户系统类型描述
 * @author fengwk
 */
@Data
public class UserSystemTypeBO {

    /**
     * 用户系统类型
     */
    private final String type;
    /**
     * 用户系统类型描述
     */
    private final String description;

    public UserSystemTypeDTO toDTO() {
        UserSystemTypeDTO userSystemTypeDTO = new UserSystemTypeDTO();
        userSystemTypeDTO.setType(getType());
        userSystemTypeDTO.setDescription(getDescription());
        return userSystemTypeDTO;
    }

}
