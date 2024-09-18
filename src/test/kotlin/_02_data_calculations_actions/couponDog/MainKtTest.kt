package _02_data_calculations_actions.couponDog

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {

    @Test
    fun subscriberRank() {

        val goodSubscriber = Subscriber("", 19)
        val bestSubscriber = Subscriber("", 20)

        assertEquals(SubscriberRank.GOOD, subscriberRank(goodSubscriber))
        assertEquals(SubscriberRank.BEST, subscriberRank(bestSubscriber))
    }

    @Test
    fun makeEmail() {
        val subscriber = Subscriber("s@s.com", 20)
        val coupons = listOf(Coupon("A", CouponRank.BEST), Coupon("B", CouponRank.BEST))

        val email = makeEmail(subscriber, coupons)

        val expected = Email(
            "newsletter@coupondog.ca",
            "s@s.com",
            "Coupons for you!",
                    "Here are some coupons for you: \nA, B")

        assertEquals(expected, email)
    }
}