package db.project.ownpliv2.artist.domain;

import db.project.ownpliv2.common.domain.BaseEntity;
import db.project.ownpliv2.common.domain.value.Domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "artist")
public class Artist extends BaseEntity {

    @Column
    private String artistName;

    protected Artist() {
        super(Domain.ARTIST);
    }
}
