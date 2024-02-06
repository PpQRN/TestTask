package service;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final String APP_URL_FILE = "/properties.properties";
    private static final Properties APP_URL_PROPERTIES = new Properties();

    static {
        initProperties(APP_URL_PROPERTIES, APP_URL_FILE);
    }

    public static String getEndPointsProperty(String property) {return APP_URL_PROPERTIES.getProperty(property);}

    public static synchronized void initProperties(Properties properties, String fileName){
        try(InputStream inputStream = PropertiesReader.class.getResourceAsStream(fileName)){
            properties.load(inputStream);
        } catch (IOException exception){
            throw new IllegalArgumentException("Can't load properties from file "+fileName);
        }
    }
}