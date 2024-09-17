package java._02_data_calculations_actions.couponDog.actions;

import java._02_data_calculations_actions.couponDog.data.Coupon;
import java._02_data_calculations_actions.couponDog.data.CouponRank;
import java._02_data_calculations_actions.couponDog.data.Subscriber;

import java.util.List;

public class DatabaseService {

    public static List<Coupon> fetchCoupons() {
        return List.of(
                new Coupon("ABC", CouponRank.GOOD),
                new Coupon("DEF", CouponRank.BAD),
                new Coupon("GHI", CouponRank.BEST),
                new Coupon("JKL", CouponRank.GOOD),
                new Coupon("MNO", CouponRank.BAD)
        );
    }

    public static List<Subscriber> fetchSubscribers() {
        return List.of(
                new Subscriber("alice@email.com", 0),
                new Subscriber("bob@email.com", 23),
                new Subscriber("charlie@email.com", 7),
                new Subscriber("dan@email.com", 3),
                new Subscriber("elise@email.com" , 0),
                new Subscriber("freda@email.com", 20),
                new Subscriber("gertrude@email.com", 2)
        );
    }
}
