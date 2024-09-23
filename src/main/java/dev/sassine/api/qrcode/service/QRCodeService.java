package dev.sassine.api.qrcode.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import dev.sassine.api.qrcode.dto.QRCodeDTO;
import dev.sassine.api.qrcode.exception.QRCodeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.Optional;

import javax.imageio.ImageIO;

@Service
public class QRCodeService {

    /**
     * Decodifica o conteúdo de um QR Code a partir de uma imagem.
     *
     * @param file Arquivo de imagem contendo o QR Code.
     * @return Conteúdo decodificado do QR Code.
     */
    public String decodeQRCode(MultipartFile file) {
        Objects.requireNonNull(file, "O arquivo não pode ser nulo");

        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                throw new IllegalArgumentException("O arquivo fornecido não é uma imagem válida.");
            }

            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result = new MultiFormatReader().decode(bitmap);

            return result.getText();

        } catch (Exception e) {
            throw new QRCodeException("Erro ao decodificar o QR Code", e);
        }
    }

    /**
     * Gera uma imagem de QR Code a partir de um conteúdo fornecido.
     *
     * @param dto Conteúdo a ser codificado no QR Code e seu tamanho
     * @return Imagem do QR Code em formato de array de bytes.
     */
    public byte[] generateQRCode(QRCodeDTO dto) {
        Objects.requireNonNull(dto.content(), "O conteúdo não pode ser nulo");

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(dto.content(),
                    BarcodeFormat.QR_CODE,
                    Optional.ofNullable(dto.width())
                            .filter(w -> w > 0)
                            .orElse(250),
                    Optional.ofNullable(dto.height())
                            .filter(h -> h > 0)
                            .orElse(250));

            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", outputStream);

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new QRCodeException("Erro ao gerar o QR Code", e);
        }
    }
}
