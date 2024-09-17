package java._02_data_calculations_actions.records;

import java.time.LocalDate;

public class Main {

        public static void main(String[] args) {

            var p1 = new Person("John", LocalDate.of(1993, 1, 3), 1.8);
            var p2 = new Person("John", LocalDate.of(2000, 2, 1), 1.6);
            var p3 = new Person("John", LocalDate.of(2000, 2, 1), 1.6);

            System.out.println(p1);
            System.out.println(p2.equals(p3));

        }
}
