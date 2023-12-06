package db.project.ownpliv2.album.service;

import db.project.ownpliv2.album.dto.AlbumMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumConsumer {

    private final AlbumService albumService;

    @RabbitListener(queues = "${rabbitmq.album-queue-name}")
    public void consumeMenuMessage(@Valid List<AlbumMessage> albumMessages) {
        albumService.updateAlbum(albumMessages);
    }
}
