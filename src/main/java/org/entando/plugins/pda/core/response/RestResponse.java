package org.entando.plugins.pda.core.response;

import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RestResponse<T, M> extends RepresentationModel<RestResponse<T, M>> {

    private T payload;
    private M metadata;
    private List<RestError> errors = new ArrayList<>();

    public RestResponse(final T payload, final M metadata, final List<RestError> errors) {
        this(payload, metadata);
        setErrors(errors);
    }

    public RestResponse(final T payload) {
        super();
        setPayload(payload);
    }

    public RestResponse(final T payload, final M metadata) {
        super();
        setPayload(payload);
        setMetaData(metadata);
    }

    @JsonSetter("metaData")
    public final void setMetaData(M metadata) {
        this.metadata = metadata;
    }

    @JsonSetter("metadata")
    public void setMetadata(M metadata) {
        this.metadata = metadata;
    }

    public void addError(final RestError error) {
        errors.add(error);
    }
}
