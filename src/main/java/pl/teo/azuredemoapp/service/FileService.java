package pl.teo.azuredemoapp.service;

import org.springframework.web.multipart.MultipartFile;
import pl.teo.azuredemoapp.model.FileMetadata;

import java.io.InputStream;

public interface FileService {
    FileMetadata upload(MultipartFile imageFile);
    InputStream download(String name);

}
