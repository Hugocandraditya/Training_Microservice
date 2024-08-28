package com.example.client;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}


	@RestController
	class HelloController {
		@GetMapping("/hello")
		public String sayHello() {
			return "Hello, Spring Boot!";
		}

		@PostMapping("/submit")
		public String submit(Model model) {
			return String.format("ID: %s, nama: %s, alamat: %s",model.getId(),model.getName(),model.getAddress());
		}

	}

	@Data
	class Model {
		private String id;
		private String name;
		private String address;
	}
}
