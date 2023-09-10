package board.controller;

import board.member.MemberRequest.CreateMemberRequest;
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

    ProjectRequest.CreateProjectRequest SportsYoung;
    ProjectRequest.CreateProjectRequest CodingHong;

    @BeforeEach
    void setUp() {
        //스터디 생성
        StudyApiControllerTest.createStudy(new StudyRequest("sports", "망원", "러닝 모집", "주 3회 인증 필수"));
        StudyApiControllerTest.createStudy(new StudyRequest("coding", "강남", "코테 스터디", "퇴근 후 모임"));

        //회원 생성
        CreateMemberRequest.MemberAddressRequest memberAddressRequest = new CreateMemberRequest.MemberAddressRequest("서울시 동작구", "상세주소", "432423");
        MemberApiTest.createMember(new CreateMemberRequest("young", "123@ggmail.com","2134",30,"coding","34fd233",memberAddressRequest));

        CreateMemberRequest.MemberAddressRequest hongAddressRequest = new CreateMemberRequest.MemberAddressRequest("서울시 동작구", "상세주소", "432423");
        MemberApiTest.createMember(new CreateMemberRequest("hong", "123@ggmail.com","2134",30,"coding","34fd233",hongAddressRequest));


        //ProjectRequest정보 생성
        SportsYoung = new ProjectRequest.CreateProjectRequest
                ("런닝 1회차", "2020-02-02", 1L, "young", 100);

        CodingHong = new ProjectRequest.CreateProjectRequest
                ("모각코 1회차", "2020-02-02", 2L, "hong", 200);

    }

    @Test
    @DisplayName("프로젝트 정보 관리")
    void createStudyTest() {
        // 프로젝트 정보 생성
        long id1 = createProject(SportsYoung);
        long id2 = createProject(CodingHong);

        // 프로젝트 정보 조회
        readProject(id1);


        // 프로젝트 정보 삭제
        deleteProject(id1);

        // 삭제된 프로젝트 정보는 조회되면 안됨
        notFoundProject(id1);
    }

//    @Test
//    @DisplayName("북마크 조회")
//    void readStudyTest() {
//        // 프로젝트 정보 생성
//        long id1 = RestAssured
//                .given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(requestSportsYoung)
//                .when()
//                .post("/project")
//                .then()
//                .statusCode(200).log().all()
//                .extract()
//                .jsonPath()
//                .getLong("id");
//
//        long id2 = RestAssured
//                .given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(requestCodingHong)
//                .when()
//                .post("/project")
//                .then()
//                .statusCode(200).log().all()
//                .extract()
//                .jsonPath()
//                .getLong("id");
//
//
//        // 스터디(sports) 북마크 조회
//
//        // 회원(홍길동) 북마크 조회
//    }

    private static void notFoundProject(long id) {
        RestAssured
                .given()
                .when()
                .get("/v1/api/project/"+ id)
                .then()
                .statusCode(500);
    }

    private static void deleteProject(long id) {
        RestAssured
                .given()
                .when()
                .delete("/v1/api/project/"+ id)
                .then()
                .statusCode(204).log().all();
    }

    private static void readProject(long id) {
        RestAssured
                .given()
                .when()
                .get("/v1/api/project/"+ id)
                .then()
                .statusCode(200).log().all();
    }

    private long createProject(ProjectRequest.CreateProjectRequest request) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/v1/api/project")
                .then()
                .statusCode(200).log().all()
                .extract()
                .jsonPath()
                .getLong("id");
    }
}
