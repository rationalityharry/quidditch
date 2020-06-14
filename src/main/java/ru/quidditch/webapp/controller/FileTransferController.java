package ru.quidditch.webapp.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.quidditch.webapp.data.entity.ImageEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.service.ImageEntityService;
import ru.quidditch.webapp.data.service.UserService;
import ru.quidditch.webapp.data.storage.StorageService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;


@RestController
@SessionAttributes(value = "user")
@RequestMapping(value = "")
public class FileTransferController {

    @Autowired
    ImageEntityService imageService;

    @Autowired
    UserService userService;

    @Autowired
    StorageService storageService;

    @PostMapping(value = "/loadImage")
    public Long submit(@RequestBody final MultipartFile file) {
        if (file == null) {
            return 0L;
        }
        ImageEntity newImage = new ImageEntity();
        newImage.setImagePath(storageService.addFile(file));
        newImage.setImageName(file.getOriginalFilename());
        return imageService.add(newImage).getAvatarImageid();
    }

    @GetMapping(value = "/user/{userId}/img")
    ResponseEntity<byte[]> getFile(@PathVariable Long userId) throws IOException {
        ResponseEntity<byte[]> respEntity;

        UserEntity user = userService.getById(userId);
        ImageEntity image = user.getUserKey();
        String address = user.getUserKey().getImagePath();
        File result = new File(address);

        if (result.exists()) {
            InputStream inputStream = new FileInputStream(address);
            String type = URLConnection.guessContentTypeFromName(image.getImageName());

            byte[] out = IOUtils.toByteArray(inputStream);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-disposition", "attachment; filename=" + image.getImageName());
            responseHeaders.setContentType(MediaType.valueOf(type));

            respEntity = new ResponseEntity<>(out, responseHeaders, HttpStatus.OK);
        } else {
            respEntity = new ResponseEntity<>("File Not Found".getBytes(), HttpStatus.OK);
        }
        return respEntity;
    }

    private static class ImageDTO {
        private MultipartFile file;

        public ImageDTO() {
        }

        public MultipartFile getFile() {
            return file;
        }

        public void setFile(final MultipartFile file) {
            this.file = file;
        }
    }

}
