package ua.in.lbn.sb2;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ua.in.lbn.code")
public class AppProperties {

    private String postgresDataDirectory;

    public String getPostgresDataDirectory() {
        return postgresDataDirectory;
    }

    public void setPostgresDataDirectory(String postgresDataDirectory) {
        this.postgresDataDirectory = postgresDataDirectory;
    }
}
