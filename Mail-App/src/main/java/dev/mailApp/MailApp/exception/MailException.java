package dev.mailApp.MailApp.exception;

public class MailException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String description;

  public MailException(String description) {
    super(description);
    this.description = description;
  }

  public static MailException noReceiverFound() {
    return new MailException("Receiver Can't be null");
  }

  public static MailException noAdapterFound() {
    return new MailException("This notification message type isn't supported");
  }

  public static MailException noNotificationTypeFound() {
    return new MailException("Notification type can't be nulll");
  }
}
