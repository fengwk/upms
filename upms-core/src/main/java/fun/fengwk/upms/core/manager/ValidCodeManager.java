package fun.fengwk.upms.core.manager;

import fun.fengwk.upms.core.model.ValidCodeConfigBO;
import fun.fengwk.upms.core.constant.ValidCodeScene;
import fun.fengwk.upms.core.constant.ValidCodeTargetType;
import fun.fengwk.upms.core.model.AppBO;
import fun.fengwk.upms.core.repo.AppRepository;
import fun.fengwk.upms.share.constant.UpmsErrorCodes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author fengwk
 */
@Slf4j
@AllArgsConstructor
@Component
public class ValidCodeManager {

    private final StringRedisTemplate redisTemplate;
    private final AppRepository appRepository;

    public void send(String appId, ValidCodeScene scene,
                     ValidCodeTargetType targetType, String target) {
        AppBO appBO = appRepository.getById(appId);
        if (appBO == null) {
            log.warn("App not found, appId: {}", appId);
            throw UpmsErrorCodes.APP_NOT_FOUND.asThrowable();
        }

        ValidCodeConfigBO validCodeConfig = appBO.getValidCodeConfig(scene, targetType);
        String code = validCodeConfig.generateCode();
        String redisKey = validCodeConfig.buildRedisKey(target);
        redisTemplate.opsForValue().set(redisKey, code, validCodeConfig.getExpireSeconds(), TimeUnit.SECONDS);
    }

    public boolean validate(String appId, ValidCodeScene scene,
                            ValidCodeTargetType targetType, String target,
                            String givenCode) {
        AppBO appBO = appRepository.getById(appId);
        if (appBO == null) {
            log.warn("App not found, appId: {}", appId);
            throw UpmsErrorCodes.APP_NOT_FOUND.asThrowable();
        }

        ValidCodeConfigBO validCodeConfig = appBO.getValidCodeConfig(scene, targetType);
        String redisKey = validCodeConfig.buildRedisKey(target);
        String code = redisTemplate.opsForValue().get(redisKey);
        return Objects.equals(code, givenCode);
    }

}
