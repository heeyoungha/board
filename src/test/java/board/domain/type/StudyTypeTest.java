package board.domain.type;

import board.study.type.StudyType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("스터디 타입 테스트")
public class StudyTypeTest {

    @Test
    @DisplayName("스터디 타입 생성")
    public void StudyTypeValueOf(){
        StudyType coding = StudyType.valueOf("coding");

        assertEquals(coding, StudyType.coding);
        assertEquals(coding.name(), "coding");
    }

    @Test
    @DisplayName("스터디 타입 생성오류")
    public void StudyTypeValueOfFail() {
        StudyType coding1 = StudyType.valueOf("coding");
        assertEquals(coding1.name(), "coding");

        StudyType coding2 = StudyType.valueOf("coding");
        //assertNull(coding2);
    }
}
