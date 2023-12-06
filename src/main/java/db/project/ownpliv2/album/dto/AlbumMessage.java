package db.project.ownpliv2.album.dto;

import db.project.ownpliv2.album.domain.Album;
import db.project.ownpliv2.artist.dto.ArtistMessage;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
public record AlbumMessage(
        String albumTitle,
        List<ArtistMessage> artist,
        String image,
        LocalDate releaseDate,
        String spotifyKey
) implements Serializable {

    public static AlbumMessage of(Album album) {
        return AlbumMessage.builder()
                .albumTitle(album.getAlbumTitle())
                .image(album.getAlbumImage())
                .releaseDate(album.getReleaseDate())
                .spotifyKey(album.getSpotifyKey())
                .build();
    }
}
