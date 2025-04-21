package com.unchk.back_portail_un_chk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BackPortailUnChkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackPortailUnChkApplication.class, args);
	}

}
