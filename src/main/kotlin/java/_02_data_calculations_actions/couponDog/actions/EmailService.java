package java._02_data_calculations_actions.couponDog.actions;

import java._02_data_calculations_actions.couponDog.data.Email;

import java.util.List;

/**
 * This class is responsible for sending emails.
 */
public class EmailService {

    /**
     * Sends emails.
     * NOTE: This doesn't actually send emails, it just prints them to the console. This is just a demo, but you can
     * imagine that this method could actually send emails using a third party library.
     *
     * @param emails A list of emails to send
     */
    public static void sendEmails(List<Email> emails) {
        for (Email email : emails) {
            System.out.println("Sending email: ");
            System.out.println(email);
            System.out.println("Email sent!");
        }
    }
}
