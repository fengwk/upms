package fun.fengwk.upms.share.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fengwk
 */
@Data
public class UserSystemDTO {

    private String code;
    private String type;
    private String name;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

}
