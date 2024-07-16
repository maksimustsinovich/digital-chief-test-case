package by.ustsinovich.testcase.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorMessage {

    private HttpStatus status;

    private Date timestamp;

    private String message;

    private String description;

    public ErrorMessage(HttpStatus status, Date timestamp, String message, String description) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
