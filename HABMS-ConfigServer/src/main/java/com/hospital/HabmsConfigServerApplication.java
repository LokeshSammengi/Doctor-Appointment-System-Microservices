package com.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class HabmsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabmsConfigServerApplication.class, args);
	}

}
