package board.study.type;

public enum StudyType {

    sports("스포츠",1),
    reading("독서",2),
    handicraft("수공예",3),
    movie("영화",4),
    coding("개발",5),
    miracle_morning("미등록",6);

    private final String description;
    private final int value;
    private final String code;

    StudyType(String description, int value){
        this.value = value;
        this.description = description;
        this.code = this.name() + value;
    }
}
