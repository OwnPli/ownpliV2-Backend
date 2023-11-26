package db.project.ownpliv2.album.service;

import db.project.ownpliv2.album.domain.Album;
import db.project.ownpliv2.album.dto.AlbumMessage;
import db.project.ownpliv2.artist.domain.Artist;
import db.project.ownpliv2.artist.dto.ArtistMessage;
import db.project.ownpliv2.repository.AlbumRepository;
import db.project.ownpliv2.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    //fixme
    public void updateAlbum(AlbumMessage albumMessage) {

        albumMessage.artist().forEach(this::updateArtist);
        albumRepository.save(Album.of(albumMessage));

    }

    public void updateArtist(ArtistMessage artistMessage) {
        if (!artistRepository.existsByArtistKey(artistMessage.artistKey())) {
            artistRepository.save(Artist.of(artistMessage));
        }
    }

}
