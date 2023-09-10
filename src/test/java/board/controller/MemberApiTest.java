package board.controller;

import board.member.MemberRequest;
import board.member.MemberRequest.CreateMemberRequest;
import board.util.AcceptanceTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

//@ActiveProfiles("test") //AcceptanceTest상속받았으므로 사실 필요없음
@DisplayName("회원 인수 테스트")
public class MemberApiTest extends AcceptanceTest {

    MemberRequest.CreateMemberRequest request;

    @BeforeEach
    void setUp() {
        CreateMemberRequest.MemberAddressRequest memberAddressRequest = new CreateMemberRequest.MemberAddressRequest("서울시 동작구", "상세주소", "432423");
        request = new CreateMemberRequest("young", "123@ggmail.com","2134",30,"coding","34fd233",memberAddressRequest);
    }

    /**
     * @see board.member.MemberController
     */
    @DisplayName("회원 정보를 관리")
    @Test
    void createMemberTest(){
        // 회원 정보 생성
        long id = createMember(request);

        // 회원 정보 조회
        readMember(id);

        // 회원 정보 삭제
        deleteMember(id);
        // 삭제된 회원 정보는 조회되면 안됨
        notFound(id);
    }

    protected static void notFound(long id) {
        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/v1/api/member/" + id)
                .then()
                .statusCode(500).log().all();
    }
    protected static void deleteMember(long id) {
        RestAssured
                .given()
                .when()
                .delete("/v1/api/member/"+ id)
                .then()
                .statusCode(204).log().all();
    }

    protected static void readMember(long id) {
        RestAssured
                .given()
                .when()
                .get("/v1/api/member/"+ id)
                .then()
                .statusCode(200).log().all();
    }

    protected static long createMember(MemberRequest.CreateMemberRequest request) {
        long id = RestAssured
                .given()
                    .body(request)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/v1/api/member")
                .then()
                    .statusCode(200).log().all()
                    .extract()
                    .jsonPath()
                    .getLong("id");
        return id;
    }
}
