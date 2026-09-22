## 실행 방법
- JDK 21 필요
- DB 설치 불필요 (H2 메모리 DB 내장)
- 아래 명령으로 실행
```bash
./gradlew bootRun
```
서버는 http://localhost:8080 에서 실행됩니다. </br>

## 프로젝트 구조
```
src/main/java/com/example/todo/
├── controller/    # API 엔드포인트
├── service/       # 비즈니스 로직
├── repository/    # DB 접근
├── entity/        # JPA 엔티티
├── dto/           # 요청/응답 DTO
└── exception/     # 예외 처리
```
