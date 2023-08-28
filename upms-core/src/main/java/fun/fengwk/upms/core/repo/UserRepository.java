package fun.fengwk.upms.core.repo;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.upms.core.model.UserBO;

/**
 * @author fengwk
 */
public interface UserRepository {

    String generateId();

    void createUserSystem(String userSystemCode);

    boolean add(String userSystemCode, UserBO userBO);

    boolean removeById(String userSystemCode, String userId);

    boolean updateSelective(String userSystemCode, UserBO selectiveBO);

    boolean existsByUsername(String userSystemCode, String username);

    boolean existsByEmail(String userSystemCode, String email);

    boolean existsByMobile(String userSystemCode, String mobile);

    boolean existsByUserId(String userSystemCode, String userId);

    UserBO getById(String userSystemCode, String userId);

    Page<UserBO> page(String userSystemCode, PageQuery pageQuery, String q);

}
