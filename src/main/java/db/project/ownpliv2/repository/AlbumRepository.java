package db.project.ownpliv2.repository;

import db.project.ownpliv2.album.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, String> {

    List<Album> findByReleaseDateBetween(LocalDate start, LocalDate end);

}
