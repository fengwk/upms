package fun.fengwk.upms.repo.mysql.model;

import fun.fengwk.convention4j.springboot.starter.cache.annotation.IdKey;
import fun.fengwk.convention4j.springboot.starter.cache.annotation.Key;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author fengwk
 */
@Data
public class UserDO {

    @IdKey
    private Long id;
    private String userSystemCode;
    @Key
    private String username;
    @Key
    private String email;
    @Key
    private String mobile;
    @Key
    private String password;
    private Map<String, Object> info;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

}
