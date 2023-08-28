package fun.fengwk.upms.core.model;

import fun.fengwk.upms.share.model.UserUpdateDTO;
import lombok.Data;

/**
 * @author fengwk
 */
@Data
public class UserUpdateBO extends UserCreateBO {

    private String id;

    public static UserUpdateBO fromDTO(UserUpdateDTO updateDTO) {
        if (updateDTO == null) {
            return null;
        }
        UserUpdateBO userUpdateBO = new UserUpdateBO();
        userUpdateBO.setId(updateDTO.getId());
        userUpdateBO.setUsername(updateDTO.getUsername());
        userUpdateBO.setEmail(updateDTO.getEmail());
        userUpdateBO.setMobile(updateDTO.getMobile());
        userUpdateBO.setPassword(updateDTO.getPassword());
        userUpdateBO.setUserInfo(updateDTO.getUserInfo());
        return userUpdateBO;
    }

}

