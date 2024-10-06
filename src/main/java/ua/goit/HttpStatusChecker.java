package ua.goit;

import ua.goit.exception.HttpWrongStatusException;

import java.io.IOException;
import java.net.*;

public class HttpStatusChecker {
    public String getStatusImage(int code) throws HttpWrongStatusException  {
        URI uri;
        URL url;
        try {
            uri = new URI("https", "http.cat", "/" + code, null);
            url = uri.toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String result;
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new HttpWrongStatusException("Status code = " + responseCode);
            }
            result = url.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return result;
    }
}
