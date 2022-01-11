# Section 1 : Service Discovery

- 서버가 3개 있을 때 주소를 사용 할 수 있는 방법

  - http://localhost:8080

    http://localhost:8081

    http://localhost:8082

  

  - http://my-server1:8080

    http://my-server2:8080

    http://my-server3:8080

  

- 우리가 만드는 모든 마이크로서비스는 Spring Cloud Netfiix Eureka에 등록된다.
  - eureka가 하는 역할이 discovery
  - 외부에서 마이크로서비스를 찾기 위해 사용하는 개념
  - 전화번호부 책과 같다(key - value) / (서버 : 서버위치)
  - 서비스 등록과 검색 = service discovery

  

- 절차
  - 각각의 마이크로 서비스 위치 정보를 Eureka 서버에 **등록** 한다.
  - 클라이언트는 자신이 필요한 요청 정보를 Load Balancer나 API Gateway에 요청
  - Load Balancer와 API Gateway가 Eureka 서버에서 정보를 찾아 전달해줌





## 프로젝트 생성

- start.spring.io 통해서 생성
- Maven
- Java 11
- Jar
  - jar는 java 명령어로 실행, war는 tomcat이나 jboss 등에 올려서 사용할 때 사용
- spring boot 2.6.2
- com.example.discoveryservice



## 환경설정

### **pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.6.2</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>discoveryservice</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>discoveryservice</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>11</java.version>
		<spring-cloud.version>2021.0.0</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

- 첫 빌드 이후에 수정 할 사항 없음





### application.yml

```yaml
server:
  port: 8761

spring:
  application:
    name: discoveryservice
    
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

- register-with-eureka : 다른 서비스가 Eureka의 정보를 알 수 있게 하는지에 대한 정보
- fetch-registry : registry에 있는 정보를 가져올지
- 위의 두 옵션을 true로 설정하면 유레카 서버에 유레카 자체의 정보를 등록하는 것과 같아서 false로 지정
  - 본인의 핸드폰 번호를 본인 핸드폰에 저장하는 것과 같은 의미다.



### DiscoveryserviceApplication.java

```java
package com.example.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryserviceApplication.class, args);
	}
}
```



- @EnableEurekaServer
  - 이 Application을 EurekaServer로 사용하겠다는 의미

