package server.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import server.entity.Role;

/**
 * Created by SunXianping on 2016/8/8 0008.
 */
public interface IRoleDao extends PagingAndSortingRepository<Role, Long> {
}
