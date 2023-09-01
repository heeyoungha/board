package board.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


//@JsonSerialize(using = MemberSerializer.class)
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponse<I> {
    private Long revNumber;
    private String timestamp;
    private String adminId;
//    private String operation;
//    private String requestMapping;
    private String revisionType;
    private I info;

    public HistoryResponse(I info, String time, String revisionType){
        this.info = info;
        this.timestamp = time;
        this.revisionType = revisionType;
    }

    public static <I> HistoryResponse<?> from(String time, String revisionType, I info) {
        return HistoryResponse.builder()
                .revisionType(revisionType)
                .timestamp(time)
                .info(info)
                .build();
    }

}
