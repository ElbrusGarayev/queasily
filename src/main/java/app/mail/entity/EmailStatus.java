package app.mail.entity;

import lombok.Data;

@Data
public class EmailStatus {
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

    private final String to;
    private final String subject;
    private final String body;

    private String status;
    private String errorMessage;

    public EmailStatus(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public EmailStatus success() {
        this.status = SUCCESS;
        return this;
    }

    public EmailStatus error(String errorMessage) {
        this.status = ERROR;
        this.errorMessage = errorMessage;
        return this;
    }
}
