package pl.teo.azuredemoapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.teo.azuredemoapp.exception.ApiNotFoundException;
import pl.teo.azuredemoapp.exception.ApiValidationException;
import pl.teo.azuredemoapp.model.Picture;
import pl.teo.azuredemoapp.repository.PictureRepo;
import pl.teo.azuredemoapp.service.FileService;
import pl.teo.azuredemoapp.service.PictureService;

import java.io.InputStream;

@Service
public class DefaultPictureService implements PictureService {
    private final PictureRepo pictureRepo;
    private final FileService fileService;

    public DefaultPictureService(PictureRepo pictureRepo, FileService fileService) {
        this.pictureRepo = pictureRepo;
        this.fileService = fileService;
    }

    @Override
    public String save(Picture picture, MultipartFile file) {
        if (pictureRepo.existsByName(picture.getName())) {
            throw new ApiValidationException("Name already taken");
        }
        picture.setFileMetadata(fileService.upload(file));
        return pictureRepo.save(picture).getName();

    }

    @Override
    public InputStream get(String name) {
        Picture picture = pictureRepo.getByName(name)
                .orElseThrow(() -> new ApiNotFoundException("Picture of that name doesn't exist"));
        String fileName = picture.getFileMetadata().getName();
        return fileService.download(fileName);
    }
}
