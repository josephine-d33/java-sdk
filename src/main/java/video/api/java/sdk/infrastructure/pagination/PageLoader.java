package video.api.java.sdk.infrastructure.pagination;

import video.api.java.sdk.domain.QueryParams;
import video.api.java.sdk.domain.exception.ResponseException;


public interface PageLoader<T> {
    Page<T> load(QueryParams queryParams) throws ResponseException;
}
