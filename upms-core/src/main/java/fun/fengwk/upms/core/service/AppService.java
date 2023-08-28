package fun.fengwk.upms.core.service;

import fun.fengwk.upms.core.repo.AppRepository;
import fun.fengwk.upms.share.model.AppCreateDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertFalse;

/**
 * @author fengwk
 */
@Slf4j
@AllArgsConstructor
@Validated
@Service
public class AppService {

    private final AppRepository appRepository;

    public void create(AppCreateDTO createDTO) {

    }

    public void updateSelective(AppCreateDTO createDTO) {

    }


}
