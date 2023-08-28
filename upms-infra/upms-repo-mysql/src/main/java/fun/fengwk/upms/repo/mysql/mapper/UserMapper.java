package fun.fengwk.upms.repo.mysql.mapper;

import fun.fengwk.automapper.annotation.AutoMapper;
import fun.fengwk.convention4j.springboot.starter.mapper.BaseMapper;

/**
 * @author fengwk
 */
@AutoMapper
public interface UserMapper extends BaseMapper {

    void createTableIfNotExists(String userSystemCode);

}