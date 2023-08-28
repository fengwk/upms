package fun.fengwk.upms.core.facade;

import fun.fengwk.auth.core.facade.AuthenticationFacade;
import fun.fengwk.convention4j.api.result.Result;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author fengwk
 */
@Component
public class UpmsAuthenticationFacade implements AuthenticationFacade {
    
    public static final String SERVER = "UPMS";

    @Override
    public String server() {
        return SERVER;
    }

    @Override
    public Result<String> authenticate(Map<String, Object> authenticationInfo) {
        return null;
    }

    @Override
    public Result<List<String>> listScopes() {
        return null;
    }

    @Override
    public Map<String, Object> getSubject(String subjectId, String scope) {
        return null;
    }

}
