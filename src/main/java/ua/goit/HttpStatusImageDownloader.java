package ua.goit;

import ua.goit.exception.HttpWrongStatusException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpStatusImageDownloader {
    public static final String IMAGES_FOLDER_PATH = "src/main/resources/images";
    public void downloadStatusImage(int code) throws HttpWrongStatusException {
        String statusImage = new HttpStatusChecker().getStatusImage(code);
        URI uri;
        URL url;
        try {
            uri = new URI(statusImage);
            url = uri.toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.openConnection();
            BufferedInputStream input = new BufferedInputStream(connection.getInputStream());
            byte[] imageBytes = input.readAllBytes();
            Files.write(Path.of(IMAGES_FOLDER_PATH + "/" + code + ".jpg"), imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new HttpStatusImageDownloader().downloadStatusImage(1000);
    }
}
