package db.project.ownpliv2.album.service;

import db.project.ownpliv2.album.domain.Album;
import db.project.ownpliv2.album.dto.AlbumMessage;
import db.project.ownpliv2.artist.domain.Artist;
import db.project.ownpliv2.artist.dto.ArtistMessage;
import db.project.ownpliv2.repository.AlbumRepository;
import db.project.ownpliv2.repository.ArtistRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @PostConstruct
    public void init() {
        LocalDate now = LocalDate.now();
        albumRepository.findByReleaseDateBetween(now.minusMonths(1), now)
                .forEach(this::putAlbumMessageInCache);
    }

    @Cacheable(value = "album", key = "")
    public AlbumMessage putAlbumMessageInCache(Album album) {
        return AlbumMessage.of(album);
    }

    public void updateAlbum(List<AlbumMessage> albumMessages) {
        albumMessages.forEach(this::addAlbumMessageInCache);
    }

    @Cacheable(value = "album", key = "#albumMessage.spotifyKey()")
    public AlbumMessage addAlbumMessageInCache(AlbumMessage albumMessage) {
        albumMessage.artist().forEach(this::updateArtist);
        albumRepository.save(Album.of(albumMessage));
        return albumMessage;
    }

    private void updateArtist(ArtistMessage artistMessage) {
        if (!artistRepository.existsByArtistKey(artistMessage.artistKey())) {
            artistRepository.save(Artist.of(artistMessage));
        }
    }

}
