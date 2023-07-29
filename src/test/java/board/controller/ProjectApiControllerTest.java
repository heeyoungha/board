package board.controller;

import board.member.Member;
import board.member.MemberRepository;
import board.member.type.Address;
import board.project.ProjectRequest;
import board.study.Study;
import board.study.StudyRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("프로젝트 인수 테스트")
@SpringBootTest
@Transactional
public class ProjectApiControllerTest {

    @Autowired MemberRepository memberRepository;
    @Autowired StudyRepository studyRepository;

    ProjectRequest.CreateProjectRequest request1;
    ProjectRequest.CreateProjectRequest request2;
    ProjectRequest.CreateProjectRequest request3;

    Member hong;
    Member young;
    Study sports;
    Study coding;

    @BeforeEach
    void setUp() {
        //스터디 생성
        sports = Study.builder()
                .study("sports")
                .build();
        coding = Study.builder()
                .study("coding")
                .build();

        studyRepository.save(sports);
        studyRepository.save(coding);

        //회원 생성
        hong = Member.builder()
                .pw("123")
                .age(20)
                .username("홍길동")
                .interest("sports")
                .address(new Address("동작구","상도동", "45466"))
                .build();
        young = Member.builder()
                .pw("123")
                .age(30)
                .username("young")
                .interest("sports")
                .address(new Address("동작구","상도동", "45466"))
                .build();
        memberRepository.save(hong);
        memberRepository.save(young);


        //ProjectRequest정보 생성
        request1 = new ProjectRequest.CreateProjectRequest
                ("런닝 1회차", "2020-02-02", "sports", "young", 100);
        request2 = new ProjectRequest.CreateProjectRequest
                ("런닝 1회차", "2020-02-02", "sports", "홍길동", 400);
        request3 = new ProjectRequest.CreateProjectRequest
                ("모각코 1회차", "2020-02-02", "coding", "홍길동", 200);

    }

    @Test
    @DisplayName("프로젝트 정보 관리")
    void createStudyTest() {


        // 프로젝트 정보 생성
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

        // 프로젝트 정보 조회
        RestAssured
                .given()
                .when()
                    .get("/project/"+id1)
                .then()
                    .statusCode(200).log().all();

        // 프로젝트 정보 삭제
        RestAssured
                .given()
                .when()
                    .delete("/project/"+id1)
                .then()
                    .statusCode(204).log().all();
        // 삭제된 프로젝트 정보는 조회되면 안됨
        RestAssured
                .given()
                .when()
                    .get("/project"+id1)
                .then()
                    .statusCode(500);
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
}
