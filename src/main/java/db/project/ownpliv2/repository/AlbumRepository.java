package db.project.ownpliv2.repository;

import db.project.ownpliv2.album.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, String> {
}
