package fun.fengwk.upms.core.model;

import fun.fengwk.upms.core.constant.ValidCodeScene;
import fun.fengwk.upms.core.constant.ValidCodeTargetType;
import lombok.Data;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author fengwk
 */
@Data
public class ValidCodeConfigBO {

    private static final String REDIS_KEY_VALID_CODE = "upms@validCode@%s@%s@%s@%s";

    private final String appId;
    private final ValidCodeScene scene;
    private final ValidCodeTargetType targetType;
    private final int digits;
    private final int expireSeconds;
    private final List<Character> validCodeChars;

    public String generateCode() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits; i++) {
            int ri = r.nextInt(Integer.MAX_VALUE) % validCodeChars.size();
            sb.append(validCodeChars.get(ri));
        }
        return sb.toString();
    }

    public String buildRedisKey(String target) {
        return String.format(REDIS_KEY_VALID_CODE, appId, scene, targetType, target);
    }

}
