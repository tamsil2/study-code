package me.tamsil.designpatterns._02_structural_patterns._10_facade._02_after;

import javax.mail.internet.AddressException;

public class Client {
    public static void main(String[] args) throws AddressException {
        EmailSettings emailSettings = new EmailSettings();
        emailSettings.setHost("127.0.0.1");
        EmailSender emailSender = new EmailSender(emailSettings);
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setFrom("kobeshow");
        emailMessage.setTo("tamsil");
        emailMessage.setSubject("오징어게임");
        emailMessage.setText("밖은 더 지옥이더라고..");
        emailSender.sendEmail(emailMessage);
    }
}
