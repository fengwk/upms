package fun.fengwk.upms.core.manager;

import fun.fengwk.upms.core.model.UserSystemBO;
import fun.fengwk.upms.core.facade.UserSystemFacade;
import fun.fengwk.upms.core.facade.UserSystemFacadeFactory;
import fun.fengwk.upms.core.repo.UserSystemRepository;
import fun.fengwk.upms.core.model.UserSystemTypeBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author fengwk
 */
@Slf4j
@Component
public class UserSystemFacadeManager {

    private final UserSystemRepository userSystemRepository;
    private final Map<String, UserSystemFacadeFactory> userSystemFacadeFactoryMap;

    public UserSystemFacadeManager(UserSystemRepository userSystemRepository, List<UserSystemFacadeFactory> userSystemFacadeFactories) {
        this.userSystemRepository = userSystemRepository;
        this.userSystemFacadeFactoryMap = userSystemFacadeFactories.stream()
            .collect(Collectors.toMap(fac -> fac.supportUserSystemType().getType(), Function.identity(), (o, n) -> {
                throw new IllegalStateException("Duplicate UserSystemFacadeFactory: " + o.supportUserSystemType());
            }));
    }

    public List<UserSystemTypeBO> listAllTypes() {
        return userSystemFacadeFactoryMap.values().stream()
            .map(UserSystemFacadeFactory::supportUserSystemType)
            .collect(Collectors.toList());
    }

    public boolean containsType(String type) {
        return userSystemFacadeFactoryMap.containsKey(type);
    }

    public UserSystemFacade getUserSystemFacade(String userSystemCode) {
        UserSystemBO userSystemBO = userSystemRepository.getByCode(userSystemCode);
        if (userSystemBO == null) {
            log.warn("UserSystem not found, userSystemCode: {}", userSystemCode);
            return null;
        }

        UserSystemFacadeFactory userSystemFacadeFactory = userSystemFacadeFactoryMap
            .get(userSystemBO.getType());
        if (userSystemFacadeFactory == null) {
            log.warn("UserSystemFacadeFactory not found, userSystemCode: {}, userSystemType: {}",
                userSystemCode, userSystemBO.getType());
            return null;
        }

        return userSystemFacadeFactory.getUserSystem(userSystemCode);
    }

}
