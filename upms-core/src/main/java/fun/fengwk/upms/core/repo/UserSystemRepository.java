package fun.fengwk.upms.core.repo;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.upms.core.model.UserSystemBO;

/**
 * @author fengwk
 */
public interface UserSystemRepository {

    boolean add(UserSystemBO userSystemBO);

    boolean updateSelective(UserSystemBO selectiveBO);

    boolean removeByCode(String code);

    boolean existsByCode(String code);

    UserSystemBO getByCode(String code);

    Page<UserSystemBO> page(PageQuery pageQuery, String q);

}
