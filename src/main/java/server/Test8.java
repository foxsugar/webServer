package server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SunXianping on 2016/8/5 0005.
 */
public class Test8 {

    public static void main(String[] args) {
        System.out.println("----------");
        List list = new ArrayList<>();
        list.add(1);
        list.add(2);
        long size = list.stream().filter(o -> {
            int i = (int)o;

            return i>1;
        }).count();
        System.out.println(size);

    }
}
