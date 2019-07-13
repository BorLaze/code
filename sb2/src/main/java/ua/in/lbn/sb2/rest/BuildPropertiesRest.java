package ua.in.lbn.sb2.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Maps.newHashMap;

@RestController
@RequestMapping("build-properties")
public class BuildPropertiesRest {

    private static final Logger log = LoggerFactory.getLogger(BuildPropertiesRest.class);

    private final Map<String, Serializable> info = newHashMap();

    public BuildPropertiesRest(BuildProperties buildProperties) {
        Assert.notNull(buildProperties, "buildProperties == null");

        if (!buildProperties.iterator().hasNext()) {
            log.warn("BuildProperties are empty!");
            return;
        }

        info.put("artifact", buildProperties.getArtifact());
        info.put("group", buildProperties.getGroup());
        info.put("java.version", buildProperties.get("java.version"));
        info.put("name", buildProperties.getName());
        info.put("time", Date.from(Instant.ofEpochMilli(Long.parseLong(buildProperties.get("time")))).toString());
        info.put("version", buildProperties.getVersion());
    }

    @GetMapping
    public ResponseEntity<Map<String, Serializable>> getBuildProperties() {
        return ResponseEntity.of(Optional.of(info));
    }
}
