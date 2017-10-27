package ru.sofitlabs.firstwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntityService;
import ru.sofitlabs.firstwebapp.data.animebase.ImageEntity;
import ru.sofitlabs.firstwebapp.data.animebase.ImageEntityService;
import ru.sofitlabs.firstwebapp.data.user.UserEntityService;
import ru.sofitlabs.firstwebapp.storage.StorageService;

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

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ImageEntity submit(@RequestBody final MultipartFile file) {
        ImageEntity newImage = new ImageEntity();
        newImage.setImagePath(storageService.addFile(file));
        newImage.setImageName(file.getOriginalFilename());
        return imageEntityService.add(newImage);
    }


}
