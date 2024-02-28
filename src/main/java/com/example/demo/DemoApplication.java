package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.apache.catalina.connector.Response;
import org.jdom2.JDOMException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import xml.JSONReponse;
import xml.xmlProcessor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@SpringBootApplication
@RestController
public class DemoApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String getMethodName() {
		return new String("hello");
	}
	
	// @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
	// public ResponseEntity<byte[]> getxml() throws IOException {
	// 	// Resource r = new ClassPathResource("demo/src/main/java/xml/cdcatalog.xml");
	// 	byte[] data = Files.readAllBytes(Path.of("demo/src/main/java/xml/cdcatalog.xml"));
	// 	return ResponseEntity.ok().body(data);
	// }

	@PostMapping(value = "/validate", consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<JSONReponse> postMethodName(@RequestBody String xmlData) {

		xmlProcessor.applyXslt(xmlData);
		try {
			JSONReponse json = xmlProcessor.getResponse();
			return ResponseEntity.ok().body(json);
		} catch (JDOMException | IOException e) {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	

}
