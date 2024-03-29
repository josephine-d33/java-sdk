package video.api.java.sdk;


import video.api.java.sdk.infrastructure.unirest.AuthRequestExecutor;
import video.api.java.sdk.infrastructure.unirest.analytic.event.AnalyticEventJsonSerializer;
import video.api.java.sdk.infrastructure.unirest.analytic.event.SessionEventAnalyticsClient;
import video.api.java.sdk.infrastructure.unirest.analytic.live.AnalyticLiveJsonSerializer;
import video.api.java.sdk.infrastructure.unirest.analytic.live.LiveStreamAnalyticsClient;
import video.api.java.sdk.infrastructure.unirest.analytic.video.AnalyticVideoJsonSerializer;
import video.api.java.sdk.infrastructure.unirest.analytic.video.VideoAnalyticsClient;
import video.api.java.sdk.infrastructure.unirest.caption.CaptionClient;
import video.api.java.sdk.infrastructure.unirest.caption.CaptionJsonSerializer;
import video.api.java.sdk.infrastructure.unirest.live.LiveStreamClient;
import video.api.java.sdk.infrastructure.unirest.live.LiveStreamJsonSerializer;
import video.api.java.sdk.infrastructure.unirest.player.PlayerClient;
import video.api.java.sdk.infrastructure.unirest.player.PlayerJsonSerializer;
import video.api.java.sdk.infrastructure.unirest.video.VideoClient;
import video.api.java.sdk.infrastructure.unirest.video.VideoJsonSerializer;

public class ClientFactory {
    public Client create(String apiKey) {
        return create(apiKey, "https://ws.api.video");
    }

    public Client createSandbox(String apiKey) {
        return create(apiKey, "https://sandbox.api.video");
    }

    private Client create(String apiKey, String baseUri) {
        AuthRequestExecutor authRequestExecutor = new AuthRequestExecutor(baseUri, apiKey);

        return new Client(
                new CaptionClient(new CaptionJsonSerializer(), authRequestExecutor, baseUri),
                new LiveStreamClient(new LiveStreamJsonSerializer(), authRequestExecutor, baseUri),
                new LiveStreamAnalyticsClient(new AnalyticLiveJsonSerializer(), authRequestExecutor, baseUri),
                new PlayerClient(new PlayerJsonSerializer(), authRequestExecutor, baseUri),
                new SessionEventAnalyticsClient(new AnalyticEventJsonSerializer(), authRequestExecutor, baseUri),
                new VideoClient(new VideoJsonSerializer(), authRequestExecutor, baseUri),
                new VideoAnalyticsClient(new AnalyticVideoJsonSerializer(), authRequestExecutor, baseUri)
        );
    }

}
