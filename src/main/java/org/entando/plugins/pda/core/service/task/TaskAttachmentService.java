package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Attachment;
import org.entando.plugins.pda.core.request.CreateAttachmentRequest;

/**
 * Defines service methods to operate on task attachments. It should be implemented if the engine supports file
 * attachment on the task.
 *
 * <p>Implementations should use the {@link Connection} argument to get access to the engine.
 *
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface TaskAttachmentService {

    /**
     * List all file attachments metadata for the given task id.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param id the task id
     * @return file attachments metadata
     */
    List<Attachment> list(Connection connection, AuthenticatedUser user, String id);

    /**
     * Get attachment metadata given the task id and the attachment id.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param id the task id
     * @param attachmentId the task attachment id
     * @return attachment metadata
     */
    Attachment get(Connection connection, AuthenticatedUser user, String id, String attachmentId);

    /**
     * Create new task attachment for the given task id.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param id the task id
     * @param attachment request details for attachment creation
     * @return attachment metadata
     */
    Attachment create(Connection connection, AuthenticatedUser user, String id, CreateAttachmentRequest attachment);

    /**
     * Delete attachment for the given task id and attachment id.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param id the task id
     * @param attachmentId the task attachments id
     * @return the attachment id of the deleted item
     */
    String delete(Connection connection, AuthenticatedUser user, String id, String attachmentId);

    /**
     * Download the file content for the attachment given the task id and attachment id.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param id the task id
     * @param attachmentId the task attachments id
     * @return byte array of the actual file
     */
    byte[] download(Connection connection, AuthenticatedUser user, String id, String attachmentId);

}
