package fun.fengwk.upms.repo.mysql;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.convention4j.common.idgen.NamespaceIdGenerator;
import fun.fengwk.upms.core.model.UserBO;
import fun.fengwk.upms.repo.mysql.mapper.UserMapper;
import fun.fengwk.upms.core.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author fengwk
 */
@AllArgsConstructor
@Repository
public class MysqlUserRepository implements UserRepository {

    private final NamespaceIdGenerator<Long> idGenerator;

    private final UserMapper userMapper;

    @Override
    public String generateId() {
        return String.valueOf(idGenerator.next(getClass()));
    }

    public void createUserSystem(String userSystemCode) {
        userMapper.createTableIfNotExists(userSystemCode);
    }

    @Override
    public boolean add(String userSystemCode, UserBO userBO) {
        return false;
    }

    @Override
    public boolean removeById(String userSystemCode, String userId) {
        return false;
    }

    @Override
    public boolean updateSelective(String userSystemCode, UserBO selectiveBO) {
        return false;
    }

    @Override
    public boolean existsByUsername(String userSystemCode, String username) {
        return false;
    }

    @Override
    public boolean existsByEmail(String userSystemCode, String email) {
        return false;
    }

    @Override
    public boolean existsByMobile(String userSystemCode, String mobile) {
        return false;
    }

    @Override
    public boolean existsByUserId(String userSystemCode, String userId) {
        return false;
    }

    @Override
    public UserBO getById(String userSystemCode, String userId) {
        return null;
    }

    @Override
    public Page<UserBO> page(String userSystemCode, PageQuery pageQuery, String q) {
        return null;
    }

}
