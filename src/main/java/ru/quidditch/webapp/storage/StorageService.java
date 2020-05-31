package ru.quidditch.webapp.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.quidditch.webapp.data.animebase.ImageEntity;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String addFile(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    MultipartFile getFile(ImageEntity image);

}
