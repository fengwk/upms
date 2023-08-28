package fun.fengwk.upms.core.facade;

import fun.fengwk.auth.core.facade.ClientFacade;
import fun.fengwk.auth.core.model.ClientBO;
import org.springframework.stereotype.Component;

/**
 * @author fengwk
 */
@Component
public class UpmsClientFacade implements ClientFacade {

    @Override
    public ClientBO getByClientId(String clientId) {
        return null;
    }

}
