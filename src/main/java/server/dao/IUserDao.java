package server.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import server.model.User;

/**
 * Created by SunXianping on 2016/8/8 0008.
 */
public interface IUserDao extends PagingAndSortingRepository<User, Long> {

}
