package server.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import server.entity.Resource;

/**
 * Created by SunXianping on 2016/8/8 0008.
 */
public interface IResourceDao extends PagingAndSortingRepository<Resource, Long> {
}
