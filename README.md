# 주문 API MSA 프로젝트

## 개요
이 레포지토리는 전자상거래 주문 관리 시스템을 구현한 마이크로서비스 아키텍처(MSA) 프로젝트의 일부입니다. 
핵심 `주문 API` 서비스와 모니터링 및 elk 스택을 통합하여, DevOps 관행을 적용한 운영 가능한 시스템을 보여줍니다. 
프로젝트는 모듈성과 확장성을 위해 다음과 같은 네 개의 구성 요소로 나뉘어 별도의 레포지토리로 관리됩니다:

1. **`msa-order-api`**: 주문 관리 API
2. **[`msa-product-api`](https://github.com/3210439/msa-product-api)**: 상품 관리 API
3. **[`elk-stack-for-msa-server`](https://github.com/3210439/elk-stack-for-msa-server)**: ELK 스택(Elasticsearch, Logstash, Kibana)을 사용한 로깅 스택.
4. **[`order-api-monitoring`](https://github.com/3210439/order-api-monitoring)**: Prometheus와 Grafana를 사용한 모니터링 스택.

이 README는 전체 시스템을 개괄적으로 설명하며, 각 레포지토리에 대한 자세한 내용은 링크를 통해 확인할 수 있습니다.

---

## 아키텍처
이 시스템은 마이크로서비스 패턴을 따르며, Docker를 활용해 구현했습니다. 아래는 전체 아키텍처 개요입니다:


- **msa-order-api**: 주문 생성 및 조회를 처리하며, Kafka로 이벤트를 발행.
- **Kafka**: 비동기 통신을 위한 메시지 브로커.
- **order-api-monitoring**: HTTP 요청 등 메트릭을 수집하고 시각화.
- **elk-stack-for-msa-server**: 로그를 집계하고 분석하여 디버깅 지원.

---

## 프로젝트 구성 요소

### msa-order-api
- **목적**: 전자상거래 시스템의 주문 관리 핵심 서비스.
- **기술 스택**:
  - 언어: Java 11 
  - 프레임워크: Spring Boot 2.7.0
  - 데이터베이스: MySQL (주문 데이터 저장용 RDBMS)
  - 메시징: Kafka (이벤트 발행)
  - API 문서화: Springdoc OpenAPI (API 문서화)
  - 메트릭: Micrometer + Actuator (Prometheus 메트릭)
  - 테스트: JUnit 5 & Mockito (단위 테스트)
  ## 주요 기능
- **주문 생성 (`POST /api/order`)**:
    - 새로운 주문을 생성하고 데이터베이스에 저장합니다.
    - 주문 이벤트(`OrderEvent`)를 Kafka 토픽(`order-event`)으로 발행하여 제품 서비스(`msa-product-api`)와 비동기적으로 통신합니다.
    - 주문 상태를 초기화하고, 사용자 ID, 제품 ID, 수량 등 필수 정보를 입력받습니다.
    - 예: 고객이 제품을 주문하면 재고 감소를 위해 이벤트를 발행.
- **주문 조회 (`GET /api/order/{id}`)**:
    - 주문 ID를 통해 특정 주문의 상세 정보를 조회합니다.
    - 데이터베이스에서 주문 데이터를 가져와 클라이언트에 JSON 응답으로 반환합니다.
    - 주문 상태, 생성 시간, 수정 시간 등 메타데이터를 포함합니다.


