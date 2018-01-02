package ru.sofitlabs.webapp.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sofitlabs.webapp.data.animebase.AnimeEntity;
import ru.sofitlabs.webapp.data.animebase.AnimeEntityService;
import ru.sofitlabs.webapp.data.animebase.ImageEntity;
import ru.sofitlabs.webapp.data.animebase.ImageEntityService;
import ru.sofitlabs.webapp.data.user.UserEntityService;
import ru.sofitlabs.webapp.storage.StorageService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;


@Controller
@SessionAttributes(value = "user")
@RequestMapping(value = "")
public class FileTransferController {

    @Autowired
    ImageEntityService imageEntityService;

    @Autowired
    AnimeEntityService animeEntityService;

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    StorageService storageService;

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

    @RequestMapping(value = "/addAnime/loadImage", method = RequestMethod.POST)
    @ResponseBody
    public ImageEntity submit(@RequestBody final MultipartFile file) {
        ImageEntity newImage = new ImageEntity();
        newImage.setImagePath(storageService.addFile(file));
        newImage.setImageName(file.getOriginalFilename());
        return imageEntityService.add(newImage);
    }

    @RequestMapping(value = "/anime/{animeId}/file", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<byte[]> getFile(@PathVariable Long animeId) throws IOException {
        ResponseEntity<byte[]> respEntity;

        AnimeEntity anime = animeEntityService.findOneById(animeId);
        ImageEntity image = imageEntityService.getOneByAnime(anime);
        String address = anime.getAnimeImage().getImagePath();
        File result = new File(anime.getAnimeImage().getImagePath());

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

}
