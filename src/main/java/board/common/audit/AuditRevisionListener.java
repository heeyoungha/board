package board.common.audit;

import org.hibernate.envers.RevisionListener;
import org.slf4j.MDC;


public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevision revision = (AuditRevision) revisionEntity;

        revision.putAdminId(MDC.get("adminId"));
        revision.putOperation(MDC.get("operation"));
        revision.putRequestMapping(MDC.get("requestMapping"));
    }
}
