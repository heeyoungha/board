package board.controller;

import board.member.MemberRequest;
import board.project.ProjectRequest;
import board.study.StudyRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

@DisplayName("프로젝트 인수 테스트")
public class ProjectApiController2Test {

    ProjectRequest.CreateProjectRequest request1;
    ProjectRequest.CreateProjectRequest request2;

    MemberRequest hong;
    StudyRequest sports;
    StudyRequest coding;

    @BeforeEach
    void setUp() {
        //스터디 정보 생성
        sports = new StudyRequest("sports");
        coding = new StudyRequest("coding");

        //회원 정보 생성
        hong = new MemberRequest("hong", "2134",30,"coding", "서울시 동작구","상세주소","432423");

        //ProjectRequest정보 생성
        request1 = new ProjectRequest.CreateProjectRequest
                ("런닝 1회차", "2020-02-02", "sports", "홍길동", 400);
        request2 = new ProjectRequest.CreateProjectRequest
                ("모각코 1회차", "2020-02-02", "coding", "홍길동", 200);

    }

    @Test
    @DisplayName("프로젝트 정보 관리")
    void createStudyTest() {
        // 스터디(sports) 정보 생성
        long S_id = RestAssured
                .given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(sports)
                .when()
                    .post("/study")
                .then()
                    .statusCode(200).log().all()
                    .extract()
                    .jsonPath()
                    .getLong("S_id");
        // 스터디(coding) 정보 생성
        long C_id = RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(coding)
                .when()
                .post("/study")
                .then()
                .statusCode(200).log().all()
                .extract()
                .jsonPath()
                .getLong("id");

        // 회원 정보 생성
        long H_id = RestAssured
                .given()
                .body(hong)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/member")
                .then()
                .statusCode(200).log().all()
                .extract()
                .jsonPath()
                .getLong("id");

        // 프로젝트 정보 생성
        long P_id = RestAssured
                .given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(request1)
                .when()
                .post("/project")
                .then()
                    .statusCode(200).log().all()
                    .extract()
                    .jsonPath()
                    .getLong("id");

        // 프로젝트 정보 조회
        RestAssured
                .given()
                .when()
                    .get("/project/"+P_id)
                .then()
                    .statusCode(200).log().all();

        // 프로젝트 정보 삭제
        RestAssured
                .given()
                .when()
                    .delete("/project/"+P_id)
                .then()
                    .statusCode(204).log().all();
        // 삭제된 프로젝트 정보는 조회되면 안됨
        RestAssured
                .given()
                .when()
                    .get("/project"+P_id)
                .then()
                    .statusCode(500);
    }
    @Test
    @DisplayName("북마크 조회")
    void readStudyTest() {
        // 프로젝트1~2 정보 생성
        long id1 = RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request1)
                .when()
                .post("/project")
                .then()
                .statusCode(200).log().all()
                .extract()
                .jsonPath()
                .getLong("id");
        // 프로젝트 정보 생성
        long id2 = RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request2)
                .when()
                .post("/project")
                .then()
                .statusCode(200).log().all()
                .extract()
                .jsonPath()
                .getLong("id");

        // 스터디(sports) 북마크 조회

        // 회원(홍길동) 북마크 조회
    }
}
