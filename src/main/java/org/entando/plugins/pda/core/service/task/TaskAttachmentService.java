package org.entando.plugins.pda.core.service.task;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Attachment;
import org.entando.plugins.pda.core.request.CreateAttachmentRequest;

public interface TaskAttachmentService {

    List<Attachment> list(Connection connection, AuthenticatedUser user, String id);

    Attachment get(Connection connection, AuthenticatedUser user, String id, String attachmentId);

    Attachment create(Connection connection, AuthenticatedUser user, String id, CreateAttachmentRequest attachment);

    String delete(Connection connection, AuthenticatedUser user, String id, String attachmentId);

    byte[] file(Connection connection, HttpServletResponse response, AuthenticatedUser user, String id,
            String attachmentId);

}
