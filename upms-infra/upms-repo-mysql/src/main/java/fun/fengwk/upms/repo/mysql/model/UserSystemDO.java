package fun.fengwk.upms.repo.mysql.model;

import fun.fengwk.automapper.annotation.UseGeneratedKeys;
import fun.fengwk.convention4j.springboot.starter.cache.annotation.IdKey;
import fun.fengwk.convention4j.springboot.starter.cache.annotation.Key;
import fun.fengwk.upms.core.model.UserSystemBO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fengwk
 */
@Data
public class UserSystemDO {

    @IdKey
    @UseGeneratedKeys
    private Long id;
    @Key
    private String code;
    @Key
    private String name;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

    public static UserSystemDO fromBO(UserSystemBO userSystemBO) {
        UserSystemDO userSystemPO = new UserSystemDO();
        userSystemPO.setCode(userSystemBO.getCode());
        userSystemPO.setName(userSystemBO.getName());
        userSystemPO.setDescription(userSystemBO.getDescription());
        userSystemPO.setCreateTime(userSystemBO.getCreateTime());
        userSystemPO.setModifiedTime(userSystemBO.getModifiedTime());
        return userSystemPO;
    }

    public UserSystemBO toBO() {
        UserSystemBO userSystemBO = new UserSystemBO();
        userSystemBO.setCode(getCode());
        userSystemBO.setName(getName());
        userSystemBO.setDescription(getDescription());
        userSystemBO.setCreateTime(getCreateTime());
        userSystemBO.setModifiedTime(getModifiedTime());
        return userSystemBO;
    }

}
