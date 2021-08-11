package pl.teo.azuredemoapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor @Getter @Setter
public class FileMetadata {
    @Id @GeneratedValue
    private int id;
    private String name;
    private String originalName;
    private Long size;
    private String contentType;


    public FileMetadata(MultipartFile multipartFile, String name) {
        this.name = name;
        this.originalName = multipartFile.getOriginalFilename();
        this.size = multipartFile.getSize();
        this.contentType = multipartFile.getContentType();
    }
}
