package ru.quidditch.webapp.data.storage;

import org.springframework.context.annotation.Configuration;

@Configuration("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
