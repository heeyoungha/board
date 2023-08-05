package board.controller;

import board.study.StudyRequest;
import board.util.AcceptanceTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DisplayName("스터디 인수 테스트")
public class StudyApiControllerTest extends AcceptanceTest {

    StudyRequest request;

    @BeforeEach
    void setUp() {
        request = new StudyRequest("sports", "망원", "망원 런닝", "주 3회 인증");
    }

    @Test
    @DisplayName("스터디 정보 관리")
    void createStudyTest() {
        // 스터디 정보 생성
        long id = createStudy(request);

        // 스터디 정보 조회
        readStudy(id);

        // 스터디 정보 삭제
        deleteStudy(id);

        // 삭제된 스터디 정보는 조회되면 안됨
        notFound(id);
    }

    protected static void notFound(long id) {
        RestAssured
                .given()
                .when()
                    .get("/study"+ id)
                .then()
                    .statusCode(500);
    }

    protected static void deleteStudy(long id) {
        RestAssured
                .given()
                .when()
                    .delete("/study/"+ id)
                .then()
                    .statusCode(204).log().all();
    }

    protected static void readStudy(long id) {
        RestAssured
                .given()
                .when()
                    .get("/study/"+ id)
                .then()
                    .statusCode(200).log().all();
    }

    protected static long createStudy(StudyRequest request) {
        return RestAssured
                .given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(request)
                .when()
                    .post("/study")
                .then()
                    .statusCode(200).log().all()
                    .extract()
                    .jsonPath()
                    .getLong("id");
    }

}
