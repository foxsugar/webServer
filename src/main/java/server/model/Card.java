package server.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by SunXianping on 2016/8/9 0009.
 */

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String name;

    public Card(String id) {
        this.id = id;
    }
}
