package server.dao;

import org.springframework.data.repository.Repository;
import server.model.Card;

import java.util.List;

/**
 * Created by SunXianping on 2016/8/9 0009.
 */
public interface ICardDao extends Repository<Card,Long> {

    void save(Card card);

    List<Card> findByName(String name);
}
