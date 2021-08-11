package pl.teo.azuredemoapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
public class Picture {
    @Id @GeneratedValue
    private int id;
    private String name;
    @OneToOne
    private FileMetadata fileMetadata;
}
