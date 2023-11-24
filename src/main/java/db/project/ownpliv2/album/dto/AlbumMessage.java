package db.project.ownpliv2.album.dto;

import db.project.ownpliv2.common.dto.Artist;

import java.util.List;

public record AlbumMessage(String albumTitle, List<Artist> artist, String image) {
}
