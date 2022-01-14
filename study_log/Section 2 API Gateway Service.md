# Section 2 API Gateway Service

- 사용자나 외부 시스템으로부터 요청을 단일화하여 처리할 수 있는 API Gateway Service



- API Gateway Service
- Netflix Ribbon과 Zuul
- Spring Cloud Gateway - 기본
- Spring Cloud Gateway - Filter
- Spring Cloud Gateway - Eureka 연동
- Spring Cloud Gateway - Load Balancer



## API Gateway Service

- 사용자가 설정한 라우팅 설정에 따라서 각각 endpoint로 클라이언트 대신해 요청하고 응답을 받으면 클라이언트에 대신 전달해주는 proxy같은 역할



- 기능
  - 인증 및 권한 부여
  - 서비스 검색 통합 (마이크로서비스 통합)
  - 응답 캐싱
  - 정책, 회로 차단기 및 QoS(트래픽 조절) 다시 시도
  - 속도 제한
  - 부하 분산
  - 로깅, 추적, 상관 관계
  - 헤더, 쿼리 문자열 및 청구 변환
  - IP 허용 목록에 추가



### Netflix Ribbon

- Spring Cloud에서의 MSA간 통신
  - 1. RestTemplate
    2. Feign Client -> 마이크로 서비스 이름으로 호출할 수 있음



- 로드 밸런서 역할을 어떻게 해야되나 고민하다가 Ribbon 서비스가 나옴

- Ribbon : Client side Load Balancer -> 비동기 처리가 잘 되지 않는 방식이라 최근엔 잘 사용 안함

  - (마이크로) 서비스 이름으로 호출

  - Health Check (정상적으로 작동중인지 확인)

  - 클라이언트 - 게이트웨이 - 서버 구조가 아니다

    클라이언트(with Ribbon) - 서버 구조이다. 클라이언트 쪽에 게이트웨이가 있는 형식





## Netflix Zuul

- Gateway 역할을 해줌
- 구성
  - First Service (마이크로 서비스)
  - Second Service (마이크로 서비스)
  - Netflix Zuul
- 클라이언트가 Neflix Zuul에 요청하면 Zuul이 First, Second에 요청을 보냄
  - 라우팅 역할
  - 게이트웨이 역할

  

- Ribbon은 Spring Cloud LoadBalancer로 대체될 수 있으며

  Zuul은 Spring Cloud Gateway로 대체될 수 있다.



- 이 부분은 강의에서만 확인하고 실제 사용은 Spring Cloud Gateway와 LoadBalancer에 집중하자