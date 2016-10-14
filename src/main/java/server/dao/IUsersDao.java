package server.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import server.entity.Users;

/**
 * Created by SunXianping on 2016/8/8 0008.
 */
public interface IUsersDao extends PagingAndSortingRepository<Users, Long> {
}
