package org.entando.plugins.pda.core.response;

import java.util.List;

public class PagedRestResponse<T> extends RestResponse<List<T>, PagedMetadata<T>> {

    @SuppressWarnings("PMD.CallSuperInConstructor")
    protected PagedRestResponse() {
        // empty constructor
    }

    public PagedRestResponse(final PagedMetadata<T> metaData) {
        super(metaData.getBody(), metaData);
    }

    public PagedRestResponse(final PagedMetadata<T> metaData, final List<RestError> errors) {
        super(metaData.getBody(), metaData, errors);
    }
}
