package dev.sassine.api.qrcode.exception;

public class QRCodeException extends RuntimeException {

    public QRCodeException(String message,Throwable cause){
        super(message, cause);
    }
}
