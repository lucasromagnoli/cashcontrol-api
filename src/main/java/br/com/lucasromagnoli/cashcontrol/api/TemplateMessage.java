package br.com.lucasromagnoli.cashcontrol.api;

import br.com.lucasromagnoli.cashcontrol.support.ObjectSupport;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@JsonPropertyOrder({"httpStatus", "messageType", "message", "contentType", "payload"})
public class TemplateMessage {
    private final MessageTypeEnum messageType;
    private String contentType;
    private final String message;
    private HttpStatus httpStatus;
    private final Object payload;

    public TemplateMessage(MessageTypeEnum messageType, String message, HttpStatus httpStatus, Object payload) {
        this.messageType = messageType;
        this.message = message;
        this.httpStatus = httpStatus;
        this.payload = payload;
    }

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Object getPayload() {
        return payload;
    }

    public String getContentType() {
        return contentType;
    }

    public ResponseEntity<TemplateMessage> toResponseEntity() {
        HttpStatus httpStatus = ObjectSupport.nvl(this.httpStatus, HttpStatus.OK);
        if (Objects.isNull(this.httpStatus)) {
            this.httpStatus = httpStatus;
        }

        if (!Objects.isNull(payload)) {
            this.contentType = payload.getClass().getSimpleName();
        }

        return ResponseEntity.status(httpStatus).body(this);
    }
}
