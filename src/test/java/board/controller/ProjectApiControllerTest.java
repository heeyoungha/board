package board.controller;

import board.member.MemberRequest;
import board.project.ProjectRequest;
import board.study.StudyRequest;
import board.util.AcceptanceTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DisplayName("프로젝트 인수 테스트")
public class ProjectApiControllerTest extends AcceptanceTest {

    ProjectRequest.CreateProjectRequest request1;
    ProjectRequest.CreateProjectRequest request2;
    ProjectRequest.CreateProjectRequest request3;

    @BeforeEach
    void setUp() {
        //스터디 생성
        StudyApiControllerTest.createStudy(new StudyRequest("sports"));
        StudyApiControllerTest.createStudy(new StudyRequest("coding"));

        //회원 생성
        MemberApiTest.createMember(new MemberRequest("young", "2134",30,"coding", "서울시 동작구","상세주소","432423"));
        MemberApiTest.createMember(new MemberRequest("hong", "2134",30,"coding", "서울시 동작구","상세주소","432423"));


        //ProjectRequest정보 생성
        request1 = new ProjectRequest.CreateProjectRequest
                ("런닝 1회차", "2020-02-02", "sports", "young", 100);

        request2 = new ProjectRequest.CreateProjectRequest
                ("런닝 1회차", "2020-02-02", "sports", "hong", 400);

        request3 = new ProjectRequest.CreateProjectRequest
                ("모각코 1회차", "2020-02-02", "coding", "hong", 200);

    }

    @Test
    @DisplayName("프로젝트 정보 관리")
    void createStudyTest() {
        // 프로젝트 정보 생성
        long id = createProject();

        // 프로젝트 정보 조회
        readProject(id);

        // 프로젝트 정보 삭제
        deleteProject(id);

        // 삭제된 프로젝트 정보는 조회되면 안됨
        notFoundProject(id);
    }

    @Test
    @DisplayName("북마크 조회")
    void readStudyTest() {
        // 프로젝트1~3 정보 생성
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
        // 프로젝트 정보 생성
        long id3 = RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request3)
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

    private static void notFoundProject(long id) {
        RestAssured
                .given()
                .when()
                .get("/project"+ id)
                .then()
                .statusCode(500);
    }

    private static void deleteProject(long id) {
        RestAssured
                .given()
                .when()
                .delete("/project/"+ id)
                .then()
                .statusCode(204).log().all();
    }

    private static void readProject(long id) {
        RestAssured
                .given()
                .when()
                .get("/project/"+ id)
                .then()
                .statusCode(200).log().all();
    }

    private long createProject() {
        return RestAssured
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
    }
}
