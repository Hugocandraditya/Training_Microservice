package com.example.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Slf4j
//@EnableDiscoveryClient
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}


	@RestController
	class HelloController {
		private static List<Model> modelList;

		{
			modelList = new ArrayList<>();
			modelList.add(new Model("1","Hugo","disana"));
			modelList.add(new Model("2","Bella","disitu"));
			modelList.add(new Model("3","Gea","disini"));
		}

		@GetMapping("/hello")
		public String sayHello() {
			log.info(modelList.toString());
			return "Hello, Spring Boot!";
		}

		@GetMapping("/get-name/{name}")
		public String getName(@PathVariable("name") String name) {
			Optional<Model> model = modelList.stream().filter(model1 -> name.equalsIgnoreCase(model1.getName())).findFirst();
			if(model.isPresent())
				return formatString(model.get());
			else
				return "data tidak ditemukan";
		}

		@PostMapping("/submit")
		public String submit(
				@RequestParam("id") String id,
				@RequestParam("name") String name,
				@RequestParam("address") String address) {

			Model model = new Model(id,name,address);
			modelList.add(model);
			return formatString(model);
		}

		private String formatString(Model model){
			return String.format("ID: %s, nama: %s, alamat: %s",model.getId(),model.getName(),model.getAddress());
		}
	}

	@Data
	@AllArgsConstructor
	class Model {
		private String id;
		private String name;
		private String address;
	}
}
