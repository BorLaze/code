package ua.in.lbn.sb2.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.boot.info.InfoProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Maps.newTreeMap;

@RestController
@RequestMapping(AppInfoRest.PATH)
public class AppInfoRest {

    static final String PATH = "app-info";

    private static final Logger log = LoggerFactory.getLogger(AppInfoRest.class);

    private final Map<String, Serializable> info = newTreeMap();

    public AppInfoRest(BuildProperties buildProperties, GitProperties gitProperties) {
        if (mergeProperties(buildProperties, "build")) {
            info.put("build.timestamp", buildProperties.getInstant("time"));
        }

        if (mergeProperties(gitProperties, "git")) {
            info.put("git.commit.timestamp", gitProperties.getInstant("commit.time"));
            info.put("git.build.timestamp", gitProperties.getInstant("build.time"));
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Serializable>> getBuildProperties() {
        return ResponseEntity.of(Optional.of(info));
    }

    private boolean mergeProperties(InfoProperties properties, String prefix) {
        if (!properties.iterator().hasNext()) {
            log.warn("{} properties are empty!", prefix);
            return false;
        }
        for (InfoProperties.Entry e : properties) {
            info.put(prefix + "." + e.getKey(), e.getValue());
        }

        return true;
    }
}
