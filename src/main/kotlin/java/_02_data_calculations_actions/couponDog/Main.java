package java._02_data_calculations_actions.couponDog;

import java._02_data_calculations_actions.couponDog.actions.DatabaseService;
import java._02_data_calculations_actions.couponDog.actions.EmailService;
import java._02_data_calculations_actions.couponDog.data.Coupon;
import java._02_data_calculations_actions.couponDog.data.CouponRank;
import java._02_data_calculations_actions.couponDog.data.Email;
import java._02_data_calculations_actions.couponDog.data.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        List<String> couponList = List.of("10% off", "20% off", "30% off", "40% off", "50% off");
//
//        var best = couponList.stream().filter(coupon -> coupon.contains("50%")).toList();
//        for ( String coupon : best ) {
//            System.out.println(coupon);
//        }

        sendNewsletter();
    }

    public static void sendNewsletter() {
        var coupons = DatabaseService.fetchCoupons();
        var goodCoupons = selectCouponsByRank(coupons, CouponRank.GOOD);
        var bestCoupons = selectCouponsByRank(coupons, CouponRank.BEST);
        var subscribers = DatabaseService.fetchSubscribers();
        var emails = couponEmailsForSubscribers(subscribers, goodCoupons, bestCoupons);
        EmailService.sendEmails(emails);
    }

    public static List<Email> couponEmailsForSubscribers(List<Subscriber> subscribers, List<Coupon> goodCoupons, List<Coupon> bestCoupons) {
        var emails = new ArrayList<Email>();
        for (Subscriber subscriber : subscribers) {
            emails.add(couponEmailForSubscriber(subscriber, goodCoupons, bestCoupons));
        }
        return emails;
    }

    public static Email couponEmailForSubscriber(Subscriber subscriber, List<Coupon> goodCoupons, List<Coupon> bestCoupons)
    {
        return switch (subscriberCouponRank(subscriber)) {
            case GOOD -> makeEmail(subscriber, goodCoupons);
            case BEST -> makeEmail(subscriber, bestCoupons);
            default -> throw new RuntimeException("We should never get here!");
        };
    }

    public static Email makeEmail(Subscriber subscriber, List<Coupon> coupons) {
        var couponStrings = toStringList(coupons);
        return new Email("newsletter@coupondog.ca",
                subscriber.email(),
                "Coupons for you!",
                "Hi there, " + subscriber.email() + "! Here are some coupons for you: \n" + String.join("\n", couponStrings) + "\n");
    }

    public static List<String> toStringList(List<Coupon> coupons) {
//        var couponStrings = new ArrayList<String>();
//        for (Coupon coupon : coupons) {
//            couponStrings.add(coupon.code());
//        }
//        return couponStrings;

        // Or, using streams:
        return coupons.stream().map(Coupon::code).toList();
    }

    public static CouponRank subscriberCouponRank(Subscriber subscriber) {
        return subscriber.referralCount() >= 20 ? CouponRank.BEST : CouponRank.GOOD;
    }

    public static List<Coupon> selectCouponsByRank(List<Coupon> coupons, CouponRank rank) {
//        return coupons.stream()
//                .filter(coupon -> coupon.rank().equals(rank))
//                .toList();

        // Using a loop:
        var selectedCoupons = new ArrayList<Coupon>();
        for (Coupon coupon : coupons) {
            if (coupon.rank().equals(rank)) {
                selectedCoupons.add(coupon);
            }
        }
        return selectedCoupons;
    }
}