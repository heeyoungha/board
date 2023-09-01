package board.common.dto;

import board.common.audit.AuditRevision;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponse<I> {
    private Long revNumber;
    private String timestamp;
    private String adminId;
    private String operation;
    private String requestMapping;
//    private String revisionType;

    //MemberInfo, StudyInfo, ProjectInfo 등
    private I info;

    public HistoryResponse(I info, String time, AuditRevision auditRevision){
        this.info = info;
        this.revNumber = auditRevision.getId();
        this.timestamp = time;
        this.adminId = auditRevision.getAdminId();
        this.operation = auditRevision.getOperation();
        this.requestMapping = auditRevision.getRequestMapping();
    }

    public static <I> HistoryResponse<?> from(String time, AuditRevision auditRevision, I info) {
        return HistoryResponse.builder()
                .revNumber(auditRevision.getId())
                .adminId(auditRevision.getAdminId())
                .operation(auditRevision.getOperation())
                .requestMapping(auditRevision.getRequestMapping())
                .timestamp(time)
                .info(info)
                .build();
    }

}
