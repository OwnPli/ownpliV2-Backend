package db.project.ownpliv2.artist.domain;

import db.project.ownpliv2.artist.dto.ArtistMessage;
import db.project.ownpliv2.common.domain.BaseEntity;
import db.project.ownpliv2.common.domain.value.Domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "artist")
public class Artist extends BaseEntity {

    @Column
    private String artistKey;

    @Column
    private String artistName;

    protected Artist() {
        super(Domain.ARTIST);
    }

    @Builder
    public Artist(Domain domain, String artistKey, String artistName) {
        super(domain);
        this.artistKey = artistKey;
        this.artistName = artistName;
    }

    public static Artist of(ArtistMessage artistMessage) {
        return Artist.builder()
                .artistKey(artistMessage.artistKey())
                .artistName(artistMessage.artistName())
                .build();

    }
}
