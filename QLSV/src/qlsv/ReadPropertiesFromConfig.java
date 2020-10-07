package qlsv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFromConfig {
	private static final String FILE_CONFIG = "\\config.properties";
    private static ReadPropertiesFromConfig instance = null;
    private Properties properties = new Properties();
    
    /**
     * Use singleton pattern to create ReadConfig object one time and use
     * anywhere
     *
     * @return instance of ReadConfig object
     */
    public static ReadPropertiesFromConfig getInstance() {
        if (instance == null) {
            instance = new ReadPropertiesFromConfig();
            instance.readConfig();
        }
        return instance;
    }
    /**
     * get property with key
     *
     * @param key
     * @return value of key
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * read file .properties
     */
    private void readConfig() {
        InputStream inputStream = null;
        try {
            String currentDir = System.getProperty("user.dir");
            inputStream = new FileInputStream(currentDir + FILE_CONFIG);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close objects
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
