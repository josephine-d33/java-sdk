package video.api.java.sdk.domain.caption;

import video.api.java.sdk.domain.exception.ResponseException;

import java.util.List;

public interface CaptionClient {

    Caption get(String VideoId, String lang) throws ResponseException;

    List<Caption> getAll(String VideoId) throws ResponseException;

    Caption upload(String VideoId, String captionSource, String lang) throws ResponseException, IllegalArgumentException;

    Caption updateDefault(String VideoId, String lang, boolean isDefault) throws ResponseException;

    int delete(String VideoId, String lang) throws ResponseException;


}
