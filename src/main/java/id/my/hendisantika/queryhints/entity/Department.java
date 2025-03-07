package id.my.hendisantika.queryhints.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-query-hints
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 08/03/25
 * Time: 06.34
 * To change this template use File | Settings | File Templates.
 */
public enum Department {
    IT,
    SALES,
    MARKETING,
    HR,
    FINANCE;

    private static final List<Department> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Department randomLetter() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
