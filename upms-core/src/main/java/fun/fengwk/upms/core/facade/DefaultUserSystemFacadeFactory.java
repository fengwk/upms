package fun.fengwk.upms.core.facade;

import fun.fengwk.upms.core.model.UserSystemTypeBO;
import fun.fengwk.upms.core.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author fengwk
 */
@AllArgsConstructor
@Component
public class DefaultUserSystemFacadeFactory implements UserSystemFacadeFactory {

    private final UserRepository userRepository;

    private static final UserSystemTypeBO DEFAULT_TYPE
        = new UserSystemTypeBO("DEFAULT", "内建");

    @Override
    public UserSystemTypeBO supportUserSystemType() {
        return DEFAULT_TYPE;
    }

    @Override
    public UserSystemFacade getUserSystem(String userSystemCode) {
        return new DefaultUserSystemFacade(userSystemCode, userRepository);
    }

}
