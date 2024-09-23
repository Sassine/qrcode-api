package dev.sassine.api.qrcode.config;

import dev.sassine.api.qrcode.exception.QRCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções do tipo RuntimeException.
     *
     * @param ex Exceção capturada.
     * @return Mensagem de erro simplificada.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(message("Ocorreu um erro ao processar sua solicitação."), HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções do tipo QRCodeException.
     *
     * @param ex Exceção capturada.
     * @return Mensagem de erro simplificada.
     */
    @ExceptionHandler(QRCodeException.class)
    public ResponseEntity<Map<String, Object>> handleQRCodeException(RuntimeException ex) {
        return new ResponseEntity<>(message("Ocorreu um erro ao processar sua solicitação."), HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções genéricas do tipo Exception.
     *
     * @param ex Exceção capturada.
     * @return Mensagem de erro simplificada.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        return new ResponseEntity<>(message("Erro interno do servidor."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> message(String message){
        return Map.of("error",message);
    }
}
