package upskill.daterra.services.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService{

    private final Path imageFolder = Paths.get("uploads");
    private final String defaultImagesFolder = "uploads";
    @Override
    public String storeImageFile(MultipartFile imageFile) {
        try {

            if (!Files.exists(imageFolder)) {
                Files.createDirectories(imageFolder);
            }

            String originalFilename = imageFile.getOriginalFilename();
            String fileExtension = getFileExtension(originalFilename);
            String uniqueFilename = UUID.randomUUID().toString() + "." + fileExtension;

            Path destinationFile = imageFolder.resolve(uniqueFilename).normalize();
            Files.copy(imageFile.getInputStream(), destinationFile);

            return uniqueFilename;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store image file: " + e.getMessage(), e);
        }
    }

    public String getDefaultImageUrl(String fileName) {
        Path filePath = Paths.get(defaultImagesFolder, fileName);
        if (Files.exists(filePath)) {
            return fileName;
        }
        return fileName;
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            throw new RuntimeException("Invalid file name: " + filename);
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

}
