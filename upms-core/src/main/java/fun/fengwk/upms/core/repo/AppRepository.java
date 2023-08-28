package fun.fengwk.upms.core.repo;

import fun.fengwk.upms.core.model.AppBO;

/**
 * @author fengwk
 */
public interface AppRepository {

    AppBO getById(String id);

}
