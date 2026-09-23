## 실행 방법
- Spring Boot 3.5.15
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
├── controller/
│   └── TodoController.java        # API 엔드포인트
├── service/
│   └── TodoService.java           # 비즈니스 로직
├── repository/
│   └── TodoRepository.java        # DB 접근
├── entity/
│   └── Todo.java                  # JPA 엔티티
├── dto/
│   ├── TodoCreateRequest.java     # 생성 요청 DTO
│   ├── TodoUpdateRequest.java     # 수정 요청 DTO
│   └── TodoResponse.java          # 응답 DTO
└── exception/
    ├── TodoNotFoundException.java # 커스텀 예외
    ├── ErrorResponse.java         # 에러 응답 형식
    └── GlobalExceptionHandler.java # 전역 예외 처리
```

## API 명세

| 기능 | 메서드 | 주소 | 요청 본문 | 응답 |
|---|---|---|---|---|
| 생성 | POST | /api/todos | `{"title": "떡볶이 먹기"}` | 200 + Todo |
| 목록 조회 | GET | /api/todos | - | 200 + Todo[] |
| 단건 조회 | GET | /api/todos/{id} | - | 200 + Todo |
| 수정 | PUT | /api/todos/{id} | `{"title": "...", "completed": true}` | 200 + Todo |
| 삭제 | DELETE | /api/todos/{id} | - | 200 |

### 오류 응답
모든 오류는 아래 형태로 통일
```bash
{ "status": 404, "message": "id 1번에 해당하는 할 일을 찾을 수 없습니다." }
```

## 설계 설명
- DB: H2 메모리 DB 사용 (별도 설치 없이 실행 명령 하나로 서버 구동 가능)
- 주소 설계: REST 관례에 따라 리소스는 복수형(`/api/todos`)으로 명명
- 완료 여부 변경: 별도 API 없이 PUT 수정 API에 포함
- 오류 응답: `status`, `message` 두 필드로 통일된 형태 유지

## API 문서 (Swagger)
서버 실행 후 아래 주소에서 확인 가능합니다.
```bash
http://localhost:8080/swagger-ui.html
```