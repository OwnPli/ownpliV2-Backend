package db.project.ownpliv2.album.dto;

import db.project.ownpliv2.artist.dto.ArtistMessage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record AlbumMessage(String albumTitle, List<ArtistMessage> artist, String image, LocalDate releaseDate, String spotifyKey)
        implements Serializable {
}
