package WatchTawer;


import java.net.*;
import java.io.*;
import java.util.*;

public class Watchtower {


    private Map<String, Integer> repeatPasswords = new HashMap<>();

    /**
     *
     * @param password
     * @return
     */
    public int lookForRepeats(String password) {
        repeatPasswords.put(password, repeatPasswords.getOrDefault(password, 0) + 1);
        int repeats = 0;
        for (String key : repeatPasswords.keySet()) {
            if (repeatPasswords.get(key) > 1) {
                repeats += repeatPasswords.get(key);
            }
        }
        return repeats;
    }

    /**
     *
     * @param website
     * @return
     * @throws IOException
     * @throws MalformedURLException
     */
    public URL standardiseWebsite(String website) throws IOException, MalformedURLException {
        //check whether the website has a protocol
        //adds if it is missing
        if ((website.startsWith("https://") || website.startsWith("https://")) == false) {
            website = "http://" + website;
        }

        URL site = new URL(website);
        HttpURLConnection connect = (HttpURLConnection) site.openConnection();
        int code;

        code = connect.getResponseCode();
        if (code == HttpURLConnection.HTTP_OK) {
            return site;
        } else if ((code == HttpURLConnection.HTTP_MOVED_TEMP
                || code == HttpURLConnection.HTTP_MOVED_PERM
                || code == HttpURLConnection.HTTP_SEE_OTHER)) {
            site = new URL(connect.getHeaderField("Location")); //looks for redirects
            connect = (HttpURLConnection) site.openConnection();
            code = connect.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                return site;
            }
        }
        return site;
    }

    /**
     *
     * @param website
     * @return
     * @throws IOException
     * @throws MalformedURLException
     */
    public boolean checkUnsecure(String website) throws IOException, MalformedURLException {
        URL site = standardiseWebsite(website);
        if (site.getProtocol().equals("http")) {
            return false;
        } else {
            return true;
        }
    }
}
