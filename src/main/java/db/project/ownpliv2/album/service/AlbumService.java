package db.project.ownpliv2.album.service;

import db.project.ownpliv2.album.domain.Album;
import db.project.ownpliv2.album.dto.AlbumMessage;
import db.project.ownpliv2.artist.domain.Artist;
import db.project.ownpliv2.artist.dto.ArtistMessage;
import db.project.ownpliv2.repository.AlbumRepository;
import db.project.ownpliv2.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    private final RedisTemplate albumRedisTemplate;

    private final ValueOperations<String, AlbumMessage> valueOperations = albumRedisTemplate.opsForValue();
    private final LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) albumRedisTemplate.getConnectionFactory();
    private final int database = connectionFactory.getDatabase();

    //fixme
    // 캐시에 있으면 앨범 저장 안하고 없으면 저장하기 구현할 것
    public void updateAlbum(List<AlbumMessage> albumMessages) {
        log.info(" album database = " + database);

        albumMessages.forEach(albumMessage -> {
            albumMessage.artist().forEach(this::updateArtist);
            valueOperations.set(albumMessage.spotifyKey(), albumMessage);
            albumRedisTemplate.expire(albumMessage.spotifyKey(), 1, TimeUnit.of(ChronoUnit.MONTHS));
            albumRepository.save(Album.of(albumMessage));
        });

    }

    public void updateArtist(ArtistMessage artistMessage) {
        if (!artistRepository.existsByArtistKey(artistMessage.artistKey())) {
            artistRepository.save(Artist.of(artistMessage));
        }
    }

}
