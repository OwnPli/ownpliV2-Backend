package db.project.ownpliv2.album.domain;

import db.project.ownpliv2.album.dto.AlbumMessage;
import db.project.ownpliv2.common.domain.BaseEntity;
import db.project.ownpliv2.common.domain.value.Domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "album")
@Getter
public class Album extends BaseEntity {

    @Column(nullable = false)
    private String albumTitle;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private String albumImage;

    protected Album() {
        super(Domain.ALBUM);
    }

    @Builder
    public Album(String albumTitle, LocalDate releaseDate, String albumImage) {
        super(Domain.ALBUM);
        this.albumTitle = albumTitle;
        this.releaseDate = releaseDate;
        this.albumImage = albumImage;
    }

    public static Album of(AlbumMessage albumMessage) {
        return Album.builder()
                .albumTitle(albumMessage.albumTitle())
                .releaseDate(albumMessage.releaseDate())
                .albumImage(albumMessage.image())
                .build();
    }
}
