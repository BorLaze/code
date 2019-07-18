package ua.in.lbn.sb2.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import static com.google.common.collect.Maps.newHashMap;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    private final ObjectMapper objectMapper;

    private HttpHeaders httpHeaders;

    public RestExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @ExceptionHandler(value = MappingException.class)
    public ResponseEntity<Object> mappingExceptionHandler(RuntimeException e, HttpServletRequest request) throws Exception {
        if (e.getCause() instanceof IllegalArgumentException) {
            return defaultExceptionHandler((RuntimeException) e.getCause(), request);
        }

        return defaultExceptionHandler(e, request);
    }

    @ExceptionHandler(value = Exception.class)
    @SuppressWarnings("squid:S00112")
    public ResponseEntity<Object> defaultExceptionHandler(Exception e, HttpServletRequest request) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        Map<String, Object> map = prepareExceptionHandlerResponse(request, e);

        request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e);
        return new ResponseEntity<>(map, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> prepareExceptionHandlerResponse(HttpServletRequest req, Exception ex) {
        HashMap<String, Object> map = newHashMap();
        map.put("message", ex.getMessage());
        map.put("method", req.getMethod());
        map.put("uri", req.getRequestURI());
        map.put("parameters", req.getParameterMap());
        map.put("errorId", UUID.randomUUID());
        map.put("body", body(req));

        if (log.isErrorEnabled()) {
            log.error(map.toString(), ex);
        }

        return map;
    }

    private Object body(HttpServletRequest req) {
        String payload = null;

        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(req, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    payload = "[UnsupportedEncodingException]";
                }
            }
        }

        try {
            return objectMapper.readValue(payload, Object.class);
        } catch (Exception e) {
            return payload;
        }
    }
}