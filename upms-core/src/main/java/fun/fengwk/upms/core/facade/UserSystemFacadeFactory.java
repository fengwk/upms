package fun.fengwk.upms.core.facade;

import fun.fengwk.upms.core.model.UserSystemTypeBO;

/**
 * @author fengwk
 */
public interface UserSystemFacadeFactory {

    /**
     * 获取支持的用户系统类型
     * @return 获取支持的用户系统类型
     */
    UserSystemTypeBO supportUserSystemType();

    /**
     * 获取用户系统
     * @param userSystemCode 用户系统编码
     * @return 用户系统
     */
    UserSystemFacade getUserSystem(String userSystemCode);

}
