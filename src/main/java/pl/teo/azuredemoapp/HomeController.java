package pl.teo.azuredemoapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.teo.azuredemoapp.model.Picture;
import pl.teo.azuredemoapp.service.PictureService;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
public class HomeController {
    private final PictureService pictureService;

    public HomeController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @PostMapping("/upload" )
    public String upload(@RequestParam("picture") String picture, @RequestParam("file") MultipartFile image) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String name = pictureService.save(mapper.readValue(picture, Picture.class), image);
        return String.format("Success! Picture available at /api/download/%s", name);
    }

    @GetMapping(value = "/download/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] get(@PathVariable String name) throws IOException {
        InputStream is = pictureService.get(name);
        return IOUtils.toByteArray(is);
    }



}
