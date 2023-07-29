package board.controller;

import board.study.StudyRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

@DisplayName("스터디 인수 테스트")
public class StudyApiControllerTest {

    StudyRequest request;

    @BeforeEach
    void setUp() {
        request = new StudyRequest("sports");
    }

    @Test
    @DisplayName("스터디 정보 관리")
    void createStudyTest() {
        // 스터디 정보 생성
        long id = RestAssured
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

        // 스터디 정보 조회
        RestAssured
                .given()
                .when()
                    .get("/study/"+id)
                .then()
                    .statusCode(200).log().all();

        // 스터디 정보 삭제
        RestAssured
                .given()
                .when()
                    .delete("/study/"+id)
                .then()
                    .statusCode(204).log().all();
        // 삭제된 스터디 정보는 조회되면 안됨
        RestAssured
                .given()
                .when()
                    .get("/study"+id)
                .then()
                    .statusCode(500);
    }

}
