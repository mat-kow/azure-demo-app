package pl.teo.azuredemoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.azuredemoapp.model.FileMetadata;

public interface FileMetadataRepo extends JpaRepository<FileMetadata, Integer> {
}
