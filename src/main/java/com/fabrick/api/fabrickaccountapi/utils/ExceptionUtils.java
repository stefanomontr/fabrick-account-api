package com.fabrick.api.fabrickaccountapi.utils;

import com.fabrick.api.fabrickaccountapi.rest.ErrorDetails;
import com.fabrick.api.fabrickaccountapi.rest.ResponseStatus;
import com.fabrick.api.fabrickaccountapi.rest.RestResponse;
import org.springframework.web.client.RestClientResponseException;

import java.util.Collections;
import java.util.Objects;

public class ExceptionUtils {

    public static <T> RestResponse<T> restClientResponseError(RestClientResponseException e) {
        var errorResponse =  (RestResponse<T>) e.getResponseBodyAs(RestResponse.class);
        if (Objects.nonNull(errorResponse)) {
            return RestResponse.<T>builder()
                    .status(errorResponse.getStatus())
                    .errors(errorResponse.getErrors())
                    .build();
        }
        return RestResponse.<T>builder()
                .status(ResponseStatus.KO)
                .errors(Collections.singletonList(ErrorDetails.builder()
                        .code(e.getStatusCode().toString())
                        .description(e.getResponseBodyAsString())
                        .build()))
                .build();
    }
}
