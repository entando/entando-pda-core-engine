package org.entando.plugins.pda.core.service.task;

import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_1_1_NAME;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_1_1_TYPE;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_1_2_NAME;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_1_2_TYPE;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_2_1_NAME;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_2_1_TYPE;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_ID_1_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_ID_1_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_ID_2_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletResponse;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.AttachmentNotFoundException;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.Attachment;
import org.entando.plugins.pda.core.request.CreateAttachmentRequest;
import org.entando.web.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FakeTaskAttachmentService implements TaskAttachmentService {

    public static final Attachment[] TASK_ATTACHMENTS_1 = new Attachment[]{
            Attachment.builder()
                    .id(TASK_ATTACHMENT_ID_1_1)
                    .name(TASK_ATTACHMENT_1_1_NAME)
                    .createdBy("admin")
                    .createdAt(new Date())
                    .size(new Random().nextLong())
                    .type(TASK_ATTACHMENT_1_1_TYPE)
                    .build(),
            Attachment.builder()
                    .id(TASK_ATTACHMENT_ID_1_2)
                    .name(TASK_ATTACHMENT_1_2_NAME)
                    .createdBy("admin")
                    .createdAt(new Date())
                    .size(new Random().nextLong())
                    .type(TASK_ATTACHMENT_1_2_TYPE)
                    .build()
    };

    public static final Attachment[] TASK_ATTACHMENTS_2 = new Attachment[]{
            Attachment.builder()
                    .id(TASK_ATTACHMENT_ID_2_1)
                    .name(TASK_ATTACHMENT_2_1_NAME)
                    .createdBy("admin")
                    .createdAt(new Date())
                    .size(new Random().nextLong())
                    .type(TASK_ATTACHMENT_2_1_TYPE)
                    .build()
    };

    private static final Map<String, List<Attachment>> ATTACHMENTS_MAP;

    static {
        ATTACHMENTS_MAP = new ConcurrentHashMap<>();
        ATTACHMENTS_MAP.put(TASK_ID_1, new ArrayList<>(Arrays.asList(TASK_ATTACHMENTS_1)));
        ATTACHMENTS_MAP.put(TASK_ID_2, new ArrayList<>(Arrays.asList(TASK_ATTACHMENTS_2)));
    }

    @Override
    public List<Attachment> list(Connection connection, AuthenticatedUser user, String id) {

        if(ATTACHMENTS_MAP.containsKey(id)) {
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
    public Attachment create(Connection connection, AuthenticatedUser user, String id, CreateAttachmentRequest request) {
        List<Attachment> attachments = ATTACHMENTS_MAP.get(id);
        if (attachments == null) {
            attachments = new ArrayList<>();
        }

        MultipartFile file = Optional.ofNullable(request.getFile())
                .orElseThrow(BadRequestException::new);

        Attachment attachment = Attachment.builder()
                .id(UUID.randomUUID().toString())
                .name(file.getName())
                .createdBy(user == null ? null : user.getUserId())
                .createdAt(new Date())
                .size(file.getSize())
                .type(file.getContentType())
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
    public byte[] file(Connection connection, HttpServletResponse response, AuthenticatedUser user, String id,
            String attachmentId) {

        Attachment attachment = get(connection, user, id, attachmentId);
        response.setContentType(attachment.getType());
        return new byte[attachment.getSize().intValue()];
    }
}
