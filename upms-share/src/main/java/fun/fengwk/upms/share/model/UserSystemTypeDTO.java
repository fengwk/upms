package fun.fengwk.upms.share.model;

import lombok.Data;

/**
 * 用户系统类型描述
 * @author fengwk
 */
@Data
public class UserSystemTypeDTO {

    /**
     * 用户系统类型
     */
    private String type;
    /**
     * 用户系统类型描述
     */
    private String description;

}
