package fun.fengwk.upms.repo.mysql;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.convention4j.common.NullSafe;
import fun.fengwk.convention4j.common.StringUtils;
import fun.fengwk.convention4j.common.page.Pages;
import fun.fengwk.upms.core.model.UserSystemBO;
import fun.fengwk.upms.repo.mysql.mapper.UserSystemMapper;
import fun.fengwk.upms.repo.mysql.model.UserSystemDO;
import fun.fengwk.upms.core.repo.UserSystemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fengwk
 */
@AllArgsConstructor
@Repository
public class MysqlUserSystemRepository implements UserSystemRepository {

    private final UserSystemMapper userSystemMapper;

    @Override
    public boolean add(UserSystemBO userSystemBO) {
        UserSystemDO userSystemPO = UserSystemDO.fromBO(userSystemBO);
        return userSystemMapper.insert(userSystemPO) > 0;
    }

    @Override
    public boolean updateSelective(UserSystemBO selectiveBO) {
        if (StringUtils.isEmpty(selectiveBO.getName()) && StringUtils.isEmpty( selectiveBO.getDescription())) {
            return true;
        }
        UserSystemDO selectivePO = UserSystemDO.fromBO(selectiveBO);
        return userSystemMapper.updateByCodeSelective(selectivePO) > 0;
    }

    @Override
    public boolean removeByCode(String code) {
        if (code == null) {
            return false;
        }
        return userSystemMapper.deleteByCode(code) > 0;
    }

    @Override
    public boolean existsByCode(String code) {
        return userSystemMapper.countByCode(code) > 0;
    }

    @Override
    public UserSystemBO getByCode(String code) {
        UserSystemDO userSystemPO  = userSystemMapper.findByCode(code);
        return NullSafe.map(userSystemPO, UserSystemDO::toBO);
    }

    @Override
    public Page<UserSystemBO> page(PageQuery pageQuery, String q) {
        List<UserSystemDO> userSystemPOs = userSystemMapper.pageByCodeContainingOrNameContaining(
            Pages.queryOffset(pageQuery), Pages.queryLimit(pageQuery), q, q);
        long totalCount = userSystemMapper.countByCodeContainingOrNameContaining(
            q, q);
        return Pages.page(pageQuery, userSystemPOs, totalCount)
            .map(UserSystemDO::toBO);
    }

}
