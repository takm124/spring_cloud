# Section 0 : MicroService와 Spring Cloud 소개

## Anti-fragile Softeware Architecture

- 시스템 변화가 적고, 비용이 저렴하고, 시스템에 적용이 쉽다, 4가지 특징을 가짐.

  

- Auto scaling

  - 자동 확장성을 가진다.
  - 예를 들어 평소에 서버를 최소한으로 유지하다가 트래픽이 몰릴 시기에 서버를 늘리는 등의 예시

    

- MicroServices

  - 서비스 단위를 잘게 쪼개서 운영
  - cloud-native architecture의 핵심



- Chaos engineering
  - 예측하지 못할 상황에도 시스템이 견딜 수 있는 것
  - 불확실성에도 안정감 있는 서비스



- Continuous deployment
  - 흔히 아는 CI/CD
  - 배포 편의성



## Cloud Native Architecture

- 확장 가능한 아키텍처
  - 시스템의 수평적 확장에 유연하다.
  - 확장된 서버로 시스템의 부하 분산, 가용성 보장
  - 시스템 또는, 서비스 애플리케이션 단위의 패키지(컨테이너 기반 패키지)
  - 모니터링

  

- 탄력적 아키텍처
  - 서비스 생성 - 통합 - 배포, 비즈니스 환경 변화에 대응 시간 단축
  - 분할 된 서비스 구조
  - 무상태 통신 프로토콜
  - 서비스의 추가와 삭제 자동으로 감지
  - 변경된 서비스 요청에 따라 사용자 요청 처리 (동적 처리)

  

- 장애 격리 (Fault Isolation)
  - 특정 서비스에 오류가 발생해도 다른 서비스에 영향을 주지 않는다.
  - 여러개의 작은 단위의 애플리케이션으로 관리되기 때문에 다른 서비스로의 영향을 최소화 한다.



  

## Cloud Native Application

- Cloud Native Architecture 에 의해 설계되고 구현되는 애플리케이션
- MicroService로 개발됨

  

- CI/CD로 자동 배포, 버전 관리
  - CI (Continuous Integration), 지속적인 통합
  - 통합 서버, 소스 관리(SCM), 빌드 도구, 테스트 도구
  - ex) Jenkins, Team CI, Travis CI
  - CD
    - Continuous Delivery = 지속적인 전달 (어느정도 수동 반영)
    - Continuous Deployment = 지속적인 배포 (운영자의 개입없는 완전한 반영)
    - Pipe line
  - 카나리 배포 (95%, 5%)
  - 블루 그린 배포 (100%)

  

- 즉시 수정, 반영 가능 (DevOps)
  - Development + Operation, 개발과 운영의 통합
  - 고객의 요구사항을 빠르게 반영하고 만족시키는 것에 목적을 둔다.

  

- Container 가상화 기능 사용
  - Cloud Native Architecture의 핵심
  - 기존의 로컬 환경에서 유지, 운영 해야만 하는 환경에서 Cloud에 이전하여 탄력성 있는 환경을 구축
  - 적은 리소스로 구축가능



## 12 Factors

- Cloud Native Application 개발을 위한 고려해야할 12개의 항목 (12factor.net)



- BASE CODE
  - 자체 repository에 저장된 각 MicroService에 대한 단일 코드 베이스
  - 버전을 제어하기 위한 목적 (형상 관리)
  - 코드의 통일적 관리



- DEPENDENCY ISOLATION

  - 종속성
  - 전체 시스템에 영향을 주지않고 수정이 가능해야 한다.

    

- CONFIGURATIONS

  - 동일한 배포가 올바른 구성에 적용된 환경에서 전파될 수 있다는 것

    

- LINKABLE BACKING SERVICES

  - 서비스 지원
  - 보조 서비스(캐시, DB, 메시징 서비스, 브로커)를 이용하여 마이크로 서비스가 가져야할 기능을 추가적으로 지원하는 것

    

- STAGES OF CREATION

  - 빌드, 릴리즈, 실행 환경을 분리하는 것
  - 각각은 고유한 ID로 관리되어야 하며 rollback필요, 자동화 구축 필요



- STATELESS PROCESSES
  - 실행중인 다른 서비스와 분리되어 자체 서비스에서 운영될 수 있어야함 (독립성)
  - 마이크로서비스 끼리 독립적 실행이 가능해야 한다는 의미
  - 캐시나 DB로 데이터 동기화



- PORT BINDING
  - 다른 마이크로 서비스와의 격리를 위함
  - 애플리케이션 끼리 서로 다른 PORT를 가지고 있어야함 (자체 PORT)

  

- CONCURRENCY
  - 동시성
  - 서비스가 분산되어있기 때문에 동시성이 보장되어야 함

  

- DISPOSABILITY
  - 서비스 인스턴스 자체가 삭제가 가능해야함
  - 확장성 기회를 높여야함
  - 정상적 종료가 가능해야 함



- DEVELOPMENT & PRODUCTION PARITY
  - 개발 단계 & 프로덕션 단계 분리
  - 환경을 분리된 채로 관리해야 한다는 의미



- LOGS
  - 로깅
  - 기존 로직과 분리되어 관리되어야 함

  

- ADMIN PROCESSES FOR EVENTUAL PROCESSES
  - 현재 운영되고 있는 모든 마이크로 서비스들을 어떤 상태로 사용되고 있으며 리소스가 현재 어떻게 쓰고 있는지 파악할 수 있는 관리도구가 필요함
  - 리포팅, 데이터 정리 분석 기능 필요



+ 최근에 세가지 factor 가 더 추가됨
  - API first
    - API 형태로 서비스 제공
  - Telemetry
    - 모든 지표는 수치화되어 시각화 되어야함
  - Authentication and Authorization
    - 인증과 인가 작업 필수



## Monolith vs MicroServices

- 애플리케이션 방법론 두 가지 (시스템 운영 구축 방식)



- Monolith 방식
  - 애플리케이션 개발 요소를 커다란 하나의 소프트웨어에 포함하여 관리함
  - 모든 서비스의 내용이 하나의 애플리케이션 내에서 운영됨
  - 각 요소의 의존도가 크다
  - 모든 업무 로직이 하나의 애플리케이션 형태로 패키지 되어 서비스
  - 애플리케이션에서 사용하는 데이터가 한곳에 모여 참조되어 서비스되는 형태

  

- MicroService 방식

  - 작은 규모의 여러 서비스들의 묶음

  - 애플리케이션의 구성요소, 서비스의 내용이 분리되어 개발 / 운영됨
  - 유지보수나 변경사항을 적용하기 유리함
  - 최소한의 중앙 집중식 관리가 필요함



## MicroService 특징

- Challenges
- Small Well Chosen Deployable Units
- Bounded Context
- RESTful
- Configuration Management
- Cloud Enabled
- Dynamic Scale Up And Scale Down
- CI/CD
- Visibility (Monitoring)



### 마이크로 아키텍처 도입 전에 생각해야 할 것

- Multiple Rates of Change (변화의 정도)
- Independent Life Cycles (서비스가 독립적으로 운용될 수 있는가)
- Independent Scalability (독립적 확장성)
- Isolated Failure (오류의 상호 영향성)
- Simplify Interactions with External Dependencies (외부 종속성 최소화, 간소화)
- Polyglot Technology (여러가지 프로그래밍 언어 호환)



## SOA, MSA 차이점

- SOA = Service Oriented Architecture
- MSA = Micro Service Architecture



- 지향점
  - SOA : 재사용을 통한 비용 절감이 목적 (서비스 공유 최대화)
  - MSA : 서비스 간의 결합도를 낮추어 변화에 능동적으로 대응하는 것을 지향 (서비스 공유 최소화) 

  

- 기술 방식
  - SOA : 공통의 서비스를 ESB에 모아 사업 측면에서 공통 서비스 형식으로 서비스 제공
  - MSA : 각 독립된 서비스가 노출된 REST API 사용

  



## RESTful Web Service

- 성숙도 모델 3가지 = REST API를 개발할 때 고려해야하는 점을 묶어 놓은 것

- LEVEL 0
  - Expose soap web services in rest style
  - 기존의 url 간단한 형태
  - http://server/getPosts
  - http://server/deletePosts
  - http://server/doThis

  

- Level 1
  - Expose resources with proper uri (적절한 리소스 표현)
  - http://server/accounts
  - http://server/accounts/10
  - note : improper use of http methods (http 별로 구분되어 사용되진 않음)

  

- Level 2
  - Level 1 + HTTP Methods
  - 제공하고 싶은 리소스를 HTTP Method에 맞게 디자인됨
  - GET, POST, PUT, DELETE에 맞게 적절하게 디자인



- Level 3
  - Level 2 + HATEOAS (무엇을 할 수 있는지에 대한 상태 정보까지 넘겨줌)
  - HATEOAS(헤테오스) =  Hypermedia as the Engine of Application State
  - Data + Next possible actions



### 고려사항 

- Consumer first : 소비자 시점 (api를 사용하는 사람)
- make best use of HTTP (HTTP의 특징을 살려서)
- Request methods (적절한 method가 사용되어야함) - get, post, put, delete
- Response Status (적절한 상태코드 전달) - 200, 404, 400, 201, 401
- No secure info in URI (보안이 필요한 정보가 노출되서는 안됨)
- User plurals (복수 형태 사용)
  - prefer /users to /user
  - prefer /users/1 to /user/1
- User nouns for resources (직관적인 명사 사용)
- For exceptions - define a consistent approach (일관적인 end point)
  - /search
  - /PUT/gists/{id}/star
  - DELETE/gists/{id}/star



## MSA 표준 구성요소

![MSA_Componets](https://media.vlpt.us/images/duckchanahn/post/a266d22c-d422-471e-bcc4-983b1c0fbc61/image.png)





## Spring Cloud 란?

- 독립적 개발을 위한 서비스(MSA)를 지원하기 위한 프레임워크

- https://spring.io/projects/spring-cloud



- Centralized configuration management
  - Spring Cloud Config Server
- Location transparency 
  - Naming Server (Eureka)
- Load Distribution (Load Balancing)
  - Ribbon (Client Side)
  - Spring Cloud Gateway(최신)
- Easier REST Clients
  - FeignClient
- Visibility and monitoring
  - Zipkin Distributed Tracing
  - Netflix API getway
- Fault Tolerance (회복성 패턴)
  - Hystrix
