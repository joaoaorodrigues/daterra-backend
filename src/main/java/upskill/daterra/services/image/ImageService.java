package upskill.daterra.services.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String storeImageFile(MultipartFile imageFile);
}
