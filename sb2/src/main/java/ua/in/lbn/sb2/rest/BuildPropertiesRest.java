package ua.in.lbn.sb2.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("build")
public class BuildPropertiesRest {

    private final BuildProperties buildProperties;

    public BuildPropertiesRest(BuildProperties buildProperties) {
        Assert.notNull(buildProperties, "buildProperties == null");
        this.buildProperties = buildProperties;
    }

    @GetMapping
    public ResponseEntity<BuildPropertiesOut> getBuildProperties() {
        return ResponseEntity.of(Optional.of(new BuildPropertiesOut(buildProperties)));
    }

    @SuppressWarnings("unused")
    public static class BuildPropertiesOut {

        final String artifact;
        final String group;
        final String javaVersion;
        final String name;
        final String time;
        final String version;

        BuildPropertiesOut(BuildProperties buildProperties) {
            this.artifact = buildProperties.getArtifact();
            this.group = buildProperties.getGroup();
            this.javaVersion = buildProperties.get("java.version");
            this.name = buildProperties.getName();
            this.time = buildProperties.get("time");
            this.version = buildProperties.getVersion();
        }

        public String getArtifact() {
            return artifact;
        }

        public String getGroup() {
            return group;
        }

        @JsonProperty("java.version")
        public String getJavaVersion() {
            return javaVersion;
        }

        public String getName() {
            return name;
        }

        public String getTime() {
            return Date.from(Instant.ofEpochMilli(Long.parseLong(time))).toString();
        }

        public String getVersion() {
            return version;
        }
    }
}
