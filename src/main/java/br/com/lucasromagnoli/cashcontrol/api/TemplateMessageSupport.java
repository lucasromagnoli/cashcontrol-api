package br.com.lucasromagnoli.cashcontrol.api;

import org.springframework.http.HttpStatus;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
public class TemplateMessageSupport {
    private HttpStatus httpStatus;
    private String message;
    private MessageTypeEnum messageType;
    private Object payload;

    public static TemplateMessageSupport begin() {
        return new TemplateMessageSupport();
    }

    public TemplateMessageSupport httpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public TemplateMessageSupport message(String message) {
        this.message = message;
        return this;
    }

    public TemplateMessageSupport messageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
        return this;
    }

    public TemplateMessageSupport payload(Object payload) {
        this.payload = payload;
        return this;
    }

    public TemplateMessage build() {
        return new TemplateMessage(messageType, message, httpStatus, payload);
    }
}
