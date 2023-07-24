package board.controller;

import board.member.MemberRequest;
import board.util.AcceptanceTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

@DisplayName("회원 인수 테스트")
public class MemberApiTest extends AcceptanceTest {

    MemberRequest request;

    @BeforeEach
    void setUp() {
        request = new MemberRequest("young", "2134",30,"coding","동작");
    }

    /**
     * @see board.member.MemberController
     */
    @DisplayName("회원 정보를 관리")
    @Test
    void createMemberTest(){
        // 회원 정보 생성
        long id = RestAssured
                .given()
                    .body(request)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/member")
                .then()
                    .statusCode(200).log().all()
                    .extract()
                    .jsonPath()
                    .getLong("id");

        // 회원 정보 조회
        RestAssured
                .given()
                .when()
                .get("/member/"+id)
                .then()
                .statusCode(200).log().all();

        // 회원 정보 삭제
        RestAssured
                .given()
                .when()
                .delete("/member/"+id)
                .then()
                .statusCode(204).log().all();
        // 삭제된 회원 정보는 조회되면 안됨
    }
}
