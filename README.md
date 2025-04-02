# 주문 API MSA 프로젝트

## 개요
이 레포지토리는 전자상거래 주문 관리 시스템을 구현한 마이크로서비스 아키텍처(MSA) 프로젝트의 일부입니다. 
핵심 `주문 API` 서비스와 모니터링 및 elk 스택을 통합하여, DevOps 관행을 적용한 운영 가능한 시스템을 보여줍니다. 
프로젝트는 모듈성과 확장성을 위해 다음과 같은 네 개의 구성 요소로 나뉘어 별도의 레포지토리로 관리됩니다:

1. **`msa-order-api`**: 주문 관리 API
2. **[`msa-product-api`](https://github.com/3210439/msa-product-api)**: 주문 관리 API
3. **[`elk-stack-for-msa-server`](https://github.com/3210439/elk-stack-for-msa-server)**: ELK 스택(Elasticsearch, Logstash, Kibana)을 사용한 로깅 스택.
4. **[`order-api-monitoring`](https://github.com/3210439/order-api-monitoring)**: Prometheus와 Grafana를 사용한 모니터링 스택.

이 README는 전체 시스템을 개괄적으로 설명하며, 각 레포지토리에 대한 자세한 내용은 링크를 통해 확인할 수 있습니다.

---

## 아키텍처
이 시스템은 마이크로서비스 패턴을 따르며, Docker를 활용해 컨테이너화와 오케스트레이션을 구현했습니다. 아래는 전체 아키텍처 개요입니다:


- **msa-order-api**: 주문 생성 및 조회를 처리하며, Kafka로 이벤트를 발행.
- **Kafka**: 비동기 통신을 위한 메시지 브로커.
- **order-api-monitoring**: HTTP 요청 등 메트릭을 수집하고 시각화.
- **elk-stack-for-msa-server**: 로그를 집계하고 분석하여 디버깅 지원.

---

## 프로젝트 구성 요소

### 1. msa-order-api (현재 레포지토리)
- **목적**: 전자상거래 시스템의 주문 관리 핵심 서비스.
- **기술 스택**:
  - Spring Boot 2.7.0
  - MySQL (주문 데이터 저장용 RDBMS)
  - Kafka (이벤트 발행)
  - Springdoc OpenAPI (API 문서화)
  - Micrometer + Actuator (Prometheus 메트릭)
- **주요 기능**:
  - POST `/api/order`: 주문 생성.
  - GET `/api/order/{id}`: 주문 ID로 조회.
- **설치 및 실행**:
  ```bash
  docker-compose up --build
  ```
