package pl.teo.azuredemoapp.service;

import org.springframework.web.multipart.MultipartFile;
import pl.teo.azuredemoapp.model.Picture;

import java.io.InputStream;

public interface PictureService {
    String save(Picture picture, MultipartFile file);
    InputStream get(String name);
}
