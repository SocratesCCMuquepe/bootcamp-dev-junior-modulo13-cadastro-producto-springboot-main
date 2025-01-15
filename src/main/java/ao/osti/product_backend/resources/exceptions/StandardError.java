package ao.osti.product_backend.resources.exceptions;

import java.time.Instant;

public class StandardError {
    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError() {
    }

    public StandardError(Instant timeStamp, Integer status, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Instant getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public StandardError timeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public StandardError status(Integer status) {
        this.status = status;
        return this;
    }

    public StandardError error(String error) {
        this.error = error;
        return this;
    }

    public StandardError message(String message) {
        this.message = message;
        return this;
    }

    public StandardError path(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " timeStamp='" + getTimeStamp() + "'" +
            ", status='" + getStatus() + "'" +
            ", error='" + getError() + "'" +
            ", message='" + getMessage() + "'" +
            ", path='" + getPath() + "'" +
            "}";
    }
}
