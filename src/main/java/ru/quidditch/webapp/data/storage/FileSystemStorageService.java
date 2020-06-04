package ru.quidditch.webapp.data.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.quidditch.webapp.data.entity.ImageEntity;
import ru.quidditch.webapp.data.service.ImageEntityService;
import ru.quidditch.webapp.data.service.UserService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    private ImageEntityService imageEntityService;

    private UserService userService;

    @Autowired
    public FileSystemStorageService(final ImageEntityService imageEntityService, final UserService userService) {
        this.imageEntityService = imageEntityService;
        this.userService = userService;
        this.rootLocation = Paths.get(imageEntityService.getRootLocation());
    }

    public Path getRootLocation() {
        return rootLocation;
    }

    @Override
    public MultipartFile getFile(ImageEntity image) {

        return null;
    }

    @Override
    public String addFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to addFile empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot addFile file with relative path outside current directory "
                                + filename);
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            return this.rootLocation.resolve(filename).toString();

        } catch (IOException e) {
            throw new StorageException("Failed to addFile file " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
