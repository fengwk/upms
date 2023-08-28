package fun.fengwk.upms.core.facade;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.upms.core.model.UserBO;
import fun.fengwk.upms.core.model.UserCreateBO;
import fun.fengwk.upms.core.model.UserUpdateBO;

/**
 * 用户系统门面
 * @author fengwk
 */
public interface UserSystemFacade {

    /**
     * 通知用户系统门面同步创建一个用户系统
     */
    void createUserSystem();

    /**
     * 创建用户
     * @param createBO 用户创建对象
     * @return 新创建的用户对象
     * @throws fun.fengwk.convention4j.api.code.ThrowableErrorCode 如果用户信息冲突将抛出该异常
     */
    UserBO createUser(UserCreateBO createBO);

    /**
     * 更新用户
     * @param updateBO 用户更新对象
     * @return 是否成功更新
     * @throws fun.fengwk.convention4j.api.code.ThrowableErrorCode 如果用户信息冲突将抛出该异常
     */
    boolean updateUserSelective(UserUpdateBO updateBO);

    /**
     * 删除用户
     * @param id 指定用户id
     * @return 指定的用户id是否被删除，如果用户不存在则返回false
     */
    boolean removeUser(String id);

    /**
     * 通过用户id查询用户对象
     * @param id 用户id
     * @return 用户对象
     */
    UserBO getUser(String id);

    /**
     * 分页查询用户
     * @param pageQuery 分页器
     * @param q 查询字段，具体的查询能力由各个用户系统实现定义
     * @return 用户分页结果
     */
    Page<UserBO> pageUser(PageQuery pageQuery, String q);

}
