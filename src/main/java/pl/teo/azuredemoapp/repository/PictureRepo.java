package pl.teo.azuredemoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teo.azuredemoapp.model.Picture;

import java.util.Optional;

public interface PictureRepo extends JpaRepository<Picture, Integer> {
    boolean existsByName(String name);
    Optional<Picture> getByName(String name);
}
