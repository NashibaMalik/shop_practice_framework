package com.krce.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader() {

        try {

            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");

            prop = new Properties();

            prop.load(fis);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public String getBrowser() {

        return prop.getProperty("browser");
    }

    public String getUrl() {

        return prop.getProperty("baseUrl");
    }

    public String getEmail() {

        return prop.getProperty("email");
    }

    public String getPassword() {

        return prop.getProperty("password");
    }

    public int getTimeout() {

        return Integer.parseInt(
                prop.getProperty("timeout"));
    }
}


