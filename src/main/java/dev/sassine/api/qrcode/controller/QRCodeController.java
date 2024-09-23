package dev.sassine.api.qrcode.controller;

import dev.sassine.api.qrcode.dto.QRCodeDTO;
import dev.sassine.api.qrcode.service.QRCodeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    /**
     * Endpoint para decodificar um QR Code a partir de uma imagem enviada.
     *
     * @param file Imagem do QR Code em formato MultipartFile.
     * @return JSON contendo o conteúdo do QR Code.
     */
    @PostMapping(value = "/decode", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> decodeQRCode(@RequestParam("file") MultipartFile file) {
        String content = qrCodeService.decodeQRCode(file);
        return ResponseEntity.ok(Map.of("content", content));
    }

    /**
     * Endpoint para gerar um QR Code a partir de um conteúdo fornecido.
     *
     * @param dto Conteúdo a ser codificado no QR Code.
     * @return Imagem do QR Code gerado em formato PNG.
     */
    @PostMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@RequestBody QRCodeDTO dto) {
        byte[] image = qrCodeService.generateQRCode(dto);
        return ResponseEntity.ok(image);
    }
}
