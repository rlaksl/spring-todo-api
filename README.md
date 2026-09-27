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
| 생성 | POST | /api/todos | `{"title": "공부하기"}` | 201 + Todo |
| 목록 조회 | GET | /api/todos | - | 200 + Todo[] |
| 단건 조회 | GET | /api/todos/{id} | - | 200 + Todo |
| 수정 | PUT | /api/todos/{id} | `{"title": "...", "completed": true}` | 200 + Todo |
| 삭제 | DELETE | /api/todos/{id} | - | 200 |

### 오류 응답
모든 오류는 아래 형태로 통일
```json
{
    "status": 404,
    "message": "id 1번에 해당하는 할 일을 찾을 수 없습니다."
}
```

## 실행 결과

### 1. 생성 (POST)

```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title": "공부하기"}'
```

응답 (201):
```json
{
  "id": 1,
  "title": "공부하기",
  "completed": false,
  "createdAt": "2026-09-27T10:38:39.929",
  "updatedAt": null
}
```

### 2. 목록 조회 (GET)

```bash
curl http://localhost:8080/api/todos
```

응답 (200):
```json
[
  {
    "id": 1,
    "title": "공부하기",
    "completed": false,
    "createdAt": "2026-09-27T10:38:39.929",
    "updatedAt": null
  }
]
```

### 3. 완료 처리 (PUT)

```bash
curl -X PUT http://localhost:8080/api/todos/1 \
  -H "Content-Type: application/json" \
  -d '{"title": "공부하기", "completed": true}'
```

응답 (200):
```json
{
  "id": 1,
  "title": "공부하기",
  "completed": true,
  "createdAt": "2026-09-27T10:38:39.929",
  "updatedAt": "2026-09-27T10:39:11.870"
}
```

### 4. 삭제 (DELETE)

```bash
curl -X DELETE http://localhost:8080/api/todos/1
```

응답 (200, 본문 없음)

### 5. 오류 - 400 (빈/공백 제목)

```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title": " "}'
```

응답 (400):
```json
{
  "status": 400,
  "message": "할 일을 입력해주세요."
}
```

### 6. 오류 - 400 (100자 초과)

```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title": "가나다...(100자 초과 문자열)"}'
```

응답 (400):
```json
{
  "status": 400,
  "message": "할 일은 100자를 넘을 수 없습니다."
}
```

### 7. 오류 - 404 (없는 id)

```bash
curl http://localhost:8080/api/todos/10
```

응답 (404):
```json
{
  "status": 404,
  "message": "id 10번에 해당하는 할 일을 찾을 수 없습니다."
}
```

## 설계 설명
- DB: H2 메모리 DB 사용 (별도 설치 없이 실행 명령 하나로 서버 구동 가능)
- 주소 설계: REST 관례에 따라 리소스는 복수형(`/api/todos`)으로 명명
- 완료 여부 변경: 제목과 완료 여부를 함께 수정할 수 있도록 별도 API를 만들지 않고 PUT 수정 API에 포함
- 생성 응답: 새로운 할 일이 생성되었음을 명확하게 나타내기 위해 `201 Created`로 응답
- 삭제 응답: 삭제 요청이 정상적으로 처리되었음을 나타내기 위해 `200 OK`로 응답
- 오류 응답: 모든 오류 응답을 `status`, `message` 두 필드로 통일하여 클라이언트가 일관된 형식으로 오류를 처리

## API 문서 (Swagger)
서버 실행 후 아래 주소에서 확인 가능합니다.
```text
http://localhost:8080/swagger-ui.html
```
