package server.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import server.model.User;

import java.util.List;

/**
 * Created by SunXianping on 2016/8/8 0008.
 */
public interface IUserDao extends PagingAndSortingRepository<User, Long> {
    List<User> findByName(String name);


}
