package org.entando.plugins.pda.core.service.task;

import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_CREATED_1_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_CREATED_1_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_CREATED_2_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_ID_1_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_ID_1_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_ID_2_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_NAME_1_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_NAME_1_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_NAME_2_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_OWNER_1_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_OWNER_1_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_OWNER_2_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_SIZE_1_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_SIZE_1_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_SIZE_2_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_TYPE_1_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_TYPE_1_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_TYPE_2_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.readFromFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.AttachmentNotFoundException;
import org.entando.plugins.pda.core.exception.BadRequestException;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.Attachment;
import org.entando.plugins.pda.core.model.File;
import org.entando.plugins.pda.core.request.CreateAttachmentRequest;
import org.springframework.stereotype.Service;

@SuppressWarnings("PMD.ExcessiveImports")
@Service
public class FakeTaskAttachmentService implements TaskAttachmentService {

    public static final Attachment[] TASK_ATTACHMENTS_1 = {
            Attachment.builder()
                    .id(TASK_ATTACHMENT_ID_1_1)
                    .name(TASK_ATTACHMENT_NAME_1_1)
                    .createdBy(TASK_ATTACHMENT_OWNER_1_1)
                    .createdAt(TASK_ATTACHMENT_CREATED_1_1)
                    .size(TASK_ATTACHMENT_SIZE_1_1)
                    .type(TASK_ATTACHMENT_TYPE_1_1)
                    .build(),
            Attachment.builder()
                    .id(TASK_ATTACHMENT_ID_1_2)
                    .name(TASK_ATTACHMENT_NAME_1_2)
                    .createdBy(TASK_ATTACHMENT_OWNER_1_2)
                    .createdAt(TASK_ATTACHMENT_CREATED_1_2)
                    .size(TASK_ATTACHMENT_SIZE_1_2)
                    .type(TASK_ATTACHMENT_TYPE_1_2)
                    .build()
    };

    public static final Attachment[] TASK_ATTACHMENTS_2 = {
            Attachment.builder()
                    .id(TASK_ATTACHMENT_ID_2_1)
                    .name(TASK_ATTACHMENT_NAME_2_1)
                    .createdBy(TASK_ATTACHMENT_OWNER_2_1)
                    .createdAt(TASK_ATTACHMENT_CREATED_2_1)
                    .size(TASK_ATTACHMENT_SIZE_2_1)
                    .type(TASK_ATTACHMENT_TYPE_2_1)
                    .build()
    };

    private static final Map<String, List<Attachment>> ATTACHMENTS_MAP = new ConcurrentHashMap<>();

    static {
        buildAttachments();
    }

    public static void buildAttachments() {
        ATTACHMENTS_MAP.clear();
        ATTACHMENTS_MAP.put(TASK_ID_1, new ArrayList<>(Arrays.asList(TASK_ATTACHMENTS_1)));
        ATTACHMENTS_MAP.put(TASK_ID_2, new ArrayList<>(Arrays.asList(TASK_ATTACHMENTS_2)));
    }

    @Override
    public List<Attachment> list(Connection connection, AuthenticatedUser user, String id) {

        if (ATTACHMENTS_MAP.containsKey(id)) {
            return ATTACHMENTS_MAP.get(id);
        }

        throw new TaskNotFoundException();
    }

    @Override
    public Attachment get(Connection connection, AuthenticatedUser user, String id, String attachmentId) {
        List<Attachment> attachments = Optional.ofNullable(ATTACHMENTS_MAP.get(id))
                .orElseThrow(AttachmentNotFoundException::new);

        for (Attachment attachment : attachments) {
            if (attachment.getId().equals(attachmentId)) {
                return attachment;
            }
        }

        throw new AttachmentNotFoundException();
    }

    @Override
    public Attachment create(Connection connection, AuthenticatedUser user, String id,
            CreateAttachmentRequest request) {
        List<Attachment> attachments = ATTACHMENTS_MAP.get(id);
        if (attachments == null) {
            attachments = new ArrayList<>();
        }

        File file = new File(Optional.ofNullable(request.getFile())
                .orElseThrow(BadRequestException::new));

        Attachment attachment = Attachment.builder()
                .id(UUID.randomUUID().toString())
                .name(file.getName())
                .createdBy(user == null ? null : user.getUserId())
                .createdAt(new Date())
                .size((long) file.getSize())
                .type(file.getType())
                .build();

        attachments.add(attachment);

        ATTACHMENTS_MAP.put(id, attachments);
        return attachment;
    }

    @Override
    public String delete(Connection connection, AuthenticatedUser user, String id, String attachmentId) {
        Attachment attachment = get(connection, user, id, attachmentId);

        List<Attachment> attachments = ATTACHMENTS_MAP.get(id);
        attachments.remove(attachment);

        ATTACHMENTS_MAP.put(id, attachments);

        return attachmentId;
    }

    @Override
    public File download(Connection connection, AuthenticatedUser user, String id,
            String attachmentId) {

        get(connection, user, id, attachmentId); //validates attachment exists
        return new File(readFromFile("task_attachment_file.txt"));
    }
}
