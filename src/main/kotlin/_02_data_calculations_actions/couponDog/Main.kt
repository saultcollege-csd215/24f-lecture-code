package _02_data_calculations_actions.couponDog

/**
 *  NOTE: This is not a real-world example. It's a simplified example to demonstrate the concepts.
 *
 * It is also not necessary to organize your code into DATA, ACTIONS, and CALCULATIONS sections, but for the
 * sake of learning to identify these parts of the code, we've done so here.
 */

/*******************
 * DATA
 */

// Let's use enum and data classes to represent the data we're working with.
enum class CouponRank { GOOD, BAD, BEST }
enum class SubscriberRank { GOOD, BEST }
data class Coupon(val code: String, val rank: CouponRank)
data class Email(val from: String, val to: String, val subject: String, val body: String)
data class Subscriber(val email: String, val referralCount: Int)

/******************
 * CALCULATIONS
 */

fun selectCouponsByRank(coupons: List<Coupon>, rank: CouponRank) =
    coupons.filter { it.rank == rank }

fun couponEmailsForSubscribers(subscribers: List<Subscriber>, goodCoupons: List<Coupon>, bestCoupons: List<Coupon>) =
    subscribers.map { couponEmailForSubscriber(it, goodCoupons, bestCoupons) }

fun couponEmailForSubscriber(subscriber: Subscriber, goodCoupons: List<Coupon>, bestCoupons: List<Coupon>) =
    when (subscriberRank(subscriber)) {
        SubscriberRank.GOOD -> makeEmail(subscriber, goodCoupons)
        SubscriberRank.BEST -> makeEmail(subscriber, bestCoupons)
    }

fun subscriberRank(subscriber: Subscriber) =
    if (subscriber.referralCount >= 20) SubscriberRank.BEST else SubscriberRank.GOOD

fun makeEmail(subscriber: Subscriber, coupons: List<Coupon>) =
    Email(
        from = "newsletter@coupondog.com",
        to = subscriber.email,
        subject = "Check out these coupons!",
        body = "Here are your coupons: ${coupons.joinToString { it.code }}")

/********************
 * ACTIONS
 */

fun main() {
    sendNewsletter()  // This is an action, so main is an action
}

fun sendNewsletter() {
    val coupons = fetchCoupons()            // This is an action, so sendNewsletter is an action
    val subscribers = fetchSubscribers()    // This is an action, so sendNewsletter is an action

    val goodCoupons = selectCouponsByRank(coupons, CouponRank.GOOD);
    val bestCoupons = selectCouponsByRank(coupons, CouponRank.BEST);
    val emails = couponEmailsForSubscribers(subscribers, goodCoupons, bestCoupons);

    sendEmails(emails)                      // This is an action, so sendNewsletter is an action
}

// This function simulates retrieving Coupons from a database
fun fetchCoupons(): List<Coupon> {
    return listOf(
        Coupon("ABC", CouponRank.GOOD),
        Coupon("DEF", CouponRank.BAD),
        Coupon("GHI", CouponRank.BEST),
        Coupon("JKL", CouponRank.GOOD),
        Coupon("MNO", CouponRank.BAD)
    )
}

// This function simulates retrieving a Subscriber list from a database
fun fetchSubscribers(): List<Subscriber> {
    return listOf(
        Subscriber("alice@email.com", 0),
        Subscriber("bob@email.com", 23),
        Subscriber("charlie@email.com", 7),
        Subscriber("dan@email.com", 3),
        Subscriber("elise@email.com", 0),
        Subscriber("freda@email.com", 20),
        Subscriber("gertrude@email.com", 2)
    )
}

// This function simulates sending an email

fun sendEmails(emails: List<Email>) {
    for (email in emails) {
        println("Sending: ")
        println(email)
    }
}