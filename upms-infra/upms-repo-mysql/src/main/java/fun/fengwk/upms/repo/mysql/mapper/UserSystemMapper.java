package fun.fengwk.upms.repo.mysql.mapper;

import fun.fengwk.automapper.annotation.AutoMapper;
import fun.fengwk.automapper.annotation.Selective;
import fun.fengwk.convention4j.springboot.starter.cache.annotation.CacheReadMethod;
import fun.fengwk.convention4j.springboot.starter.cache.annotation.CacheWriteMethod;
import fun.fengwk.convention4j.springboot.starter.cache.annotation.IdKey;
import fun.fengwk.convention4j.springboot.starter.cache.annotation.Key;
import fun.fengwk.convention4j.springboot.starter.mapper.BaseMapper;
import fun.fengwk.upms.repo.mysql.model.UserSystemDO;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author fengwk
 */
@AutoMapper
public interface UserSystemMapper extends BaseMapper {

    @CacheWriteMethod
    int insert(UserSystemDO userSystemPO);

    @CacheWriteMethod
    int updateByCodeSelective(UserSystemDO selectivePO);

    @CacheWriteMethod
    int deleteByCode(@IdKey("code") String code);

    @CacheReadMethod
    int countByCode(@Key("code") String code);

    @CacheReadMethod
    UserSystemDO findByCode(@IdKey("code") String code);

    @CacheReadMethod
    int countByCodeContainingOrNameContaining(
        @Selective @Param("code") String code, @Selective @Param("name") String name);

    @CacheReadMethod
    List<UserSystemDO> pageByCodeContainingOrNameContaining(
        @Param("offset") long offset, @Param("limit") int limit,
        @Selective @Param("code") String code, @Selective @Param("name") String name);

}