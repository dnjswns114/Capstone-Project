package com.hooong.simpleMember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@EnableJpaAuditing // 데이터 변동이 있으면 JPA가 반영해줌
@SpringBootApplication
public class SimpleMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleMemberApplication.class, args);
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

}


