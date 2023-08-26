package board.common.audit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@RevisionEntity(AuditRevisionListener.class)
@Table(name = "revision")
public class AuditRevision {

    @RevisionNumber
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rev")
    private Long id;

    @RevisionTimestamp
    @Column(name = "revstamp")
    private Long timestamp;

    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "operation")
    private String operation;

    @Column(name = "requestMapping")
    private String requestMapping;

    public void putAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void putOperation(String operation) {
        this.operation = operation;
    }
    public void putRequestMapping(String requestMapping) {
        this.requestMapping = requestMapping;
    }
}
