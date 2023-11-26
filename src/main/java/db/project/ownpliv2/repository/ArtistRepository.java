package db.project.ownpliv2.repository;

import db.project.ownpliv2.artist.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, String> {

    boolean existsByArtistKey(String artistKey);

}
