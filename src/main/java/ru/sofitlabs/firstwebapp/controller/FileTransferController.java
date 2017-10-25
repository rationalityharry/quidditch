package ru.sofitlabs.firstwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntityService;
import ru.sofitlabs.firstwebapp.data.animebase.ImageEntity;
import ru.sofitlabs.firstwebapp.data.animebase.ImageEntityService;
import ru.sofitlabs.firstwebapp.data.user.UserEntityService;

import java.io.IOException;

@Controller
@SessionAttributes(value = "user")
@RequestMapping(value = {"/addAnime/loadImage", "/anime/{id}/file"})
public class FileTransferController {

    @Autowired
    ImageEntityService imageEntityService;

    @Autowired
    AnimeEntityService animeEntityService;

    @Autowired
    UserEntityService userEntityService;

    private final StorageService storageService;
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public long submit(@RequestBody final ImageDTO file) throws IOException {

        return 0;
    }


}
