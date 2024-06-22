package main;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.core.io.UrlResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 参考：{@link ImportCandidates#load(Class, ClassLoader)}
 *
 * @author suyh
 * @since 2024-01-13
 */
public class DemoLoadSpringBootImport {
    // springboot 读 *.imports spi 的调用方法。
    // 参考：ImportCandidates

    private static final String LOCATION = "META-INF/spring/%s.imports";

    public static void main(String[] args) throws IOException {
        {
            /*
             *
             * No qualifying bean of type 'org.springframework.boot.web.servlet.server.ServletWebServerFactory'
             * Unable to start AnnotationConfigServletWebServerApplicationContext due to missing ServletWebServerFactory bean
             *
             * org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory  implement ServletWebServerFactory
             *
             * org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryConfiguration.EmbeddedTomcat
             *
             * org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
             *
             *
             * META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
             */
            List<String> importCandidates = new ArrayList<>();

            // ImportCandidates importCandidates = ImportCandidates.load(AutoConfiguration.class, DemoLoadSpringBootImport.class.getClassLoader());
            ClassLoader currentClassLoader = DemoLoadSpringBootImport.class.getClassLoader();
            // META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
            String location = String.format(LOCATION, AutoConfiguration.class);
            Enumeration<URL> urls = currentClassLoader.getResources(location);

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                System.out.println("suyh - url: " + url.toString());
                List<String> list = readCandidateConfigurations(url);
                for (String clazz : list) {
                    System.out.println("class name: " + clazz);
                }
                importCandidates.addAll(list);
            }

            System.out.println("importCandidates size: " + importCandidates.size());
        }
    }

    private static List<String> readCandidateConfigurations(URL url) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new UrlResource(url).getInputStream(), StandardCharsets.UTF_8))) {
            List<String> candidates = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                line = stripComment(line);
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                candidates.add(line);
            }
            return candidates;
        } catch (IOException ex) {
            throw new IllegalArgumentException("Unable to load configurations from location [" + url + "]", ex);
        }
    }

    private static final String COMMENT_START = "#";

    private static String stripComment(String line) {
        int commentStart = line.indexOf(COMMENT_START);
        if (commentStart == -1) {
            return line;
        }
        return line.substring(0, commentStart);
    }
}
