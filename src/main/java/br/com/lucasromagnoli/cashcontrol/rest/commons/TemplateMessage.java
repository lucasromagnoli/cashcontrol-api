package br.com.lucasromagnoli.cashcontrol.rest.commons;

import br.com.lucasromagnoli.javaee.useful.support.object.ObjectSupport;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonPropertyOrder({"httpStatus", "messageType", "message", "payload"})
public class TemplateMessage {
    private MessageTypeEnum messageType;
    private String message;
    private HttpStatus httpStatus;
    private Object payload;

    public TemplateMessage(MessageTypeEnum messageType, String message, HttpStatus httpStatus, Object payload) {
        this.messageType = messageType;
        this.message = message;
        this.httpStatus = httpStatus;
        this.payload = payload;
    }

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public ResponseEntity<TemplateMessage> toResponseEntity() {
        HttpStatus httpStatus = ObjectSupport.Nvl(this.httpStatus, HttpStatus.OK);
        if (ObjectSupport.isNull(this.httpStatus)) {
            this.httpStatus = httpStatus;
        }
        return ResponseEntity.status(httpStatus).body(this);
    }
}