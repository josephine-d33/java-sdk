package video.api.java.sdk.infrastructure.unirest.analytic.live;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import video.api.java.sdk.domain.analytic.analyticLive.AnalyticLive;
import video.api.java.sdk.domain.analytic.models.AnalyticData;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.List;

public class AnalyticLiveJsonSerializer implements JsonSerializer<AnalyticLive> {

    @Override
    public AnalyticLive deserialize(JSONObject data) throws JSONException {
        AnalyticLive analyticLive = new AnalyticLive();
        analyticLive.liveStreamId = data.getJSONObject("live").getString("liveStreamId");
        if (data.getJSONObject("live").has("name"))
            analyticLive.liveName = data.getJSONObject("live").getString("name");
        if (data.getJSONObject("live").has("period"))
            analyticLive.period = data.getString("period");
        JSONArray dataArray = data.getJSONArray("data");
        analyticLive.data = new AnalyticData[dataArray.length()];

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject   playerSession = dataArray.getJSONObject(i);
            AnalyticData analyticData  = new AnalyticData();

            // Build Analytic Session
            if (playerSession.has("session")) {
                analyticData.session.sessionId = playerSession.getJSONObject("session").getString("sessionId");
                analyticData.session.endedAt   = playerSession.getJSONObject("session").getString("endedAt");
                analyticData.session.loadedAt  = playerSession.getJSONObject("session").getString("loadedAt");
            }
            // Build Analytic Location
            if (playerSession.has("location")) {
                analyticData.location.country = playerSession.getJSONObject("location").getString("country");
                analyticData.location.city    = playerSession.getJSONObject("location").getString("city");
            }
            // Build Analytic Referer
            if (playerSession.has("referrer")) {
                analyticData.referer.medium      = playerSession.getJSONObject("referrer").getString("medium");
                analyticData.referer.search_term = playerSession.getJSONObject("referrer").getString("searchTerm");
                analyticData.referer.source      = playerSession.getJSONObject("referrer").getString("source");
                analyticData.referer.url         = playerSession.getJSONObject("referrer").getString("url");
            }
            // Build Analytic Device
            if (playerSession.has("device")) {
                analyticData.device.model  = playerSession.getJSONObject("device").getString("model");
                analyticData.device.type   = playerSession.getJSONObject("device").getString("type");
                analyticData.device.vendor = playerSession.getJSONObject("device").getString("vendor");
            }
            // Build Analytic Os
            if (playerSession.has("os")) {
                analyticData.os.name      = playerSession.getJSONObject("os").getString("name");
                analyticData.os.shortname = playerSession.getJSONObject("os").getString("shortname");
                analyticData.os.version   = playerSession.getJSONObject("os").getString("version");
            }
            // Build Analytic Client
            if (playerSession.has("client")) {
                analyticData.client.type    = playerSession.getJSONObject("client").getString("type");
                analyticData.client.name    = playerSession.getJSONObject("client").getString("name");
                analyticData.client.version = playerSession.getJSONObject("client").getString("version");
            }
            analyticLive.data[i] = analyticData;

        }


        return analyticLive;
    }

    @Override
    public JSONObject serialize(AnalyticLive analyticLive) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject live = new JSONObject();


        live.put("liveStreamId", analyticLive.liveStreamId);
        live.put("name", analyticLive.liveName);
        data.put("live", live);
        data.put("period", analyticLive.period);


        JSONArray dataArray = new JSONArray();

        for (int i = 0; i < analyticLive.data.length; i++) {
            AnalyticData analyticData  = analyticLive.data[i];
            JSONObject   playerSession = new JSONObject();


            // Build Analytic Session
            JSONObject session = new JSONObject();
            session.put("sessionId", analyticData.session.sessionId);
            session.put("endedAt", analyticData.session.endedAt);
            session.put("loadedAt", analyticData.session.loadedAt);
            playerSession.put("session", session);

            // Build Analytic Location
            JSONObject location = new JSONObject();
            location.put("country", analyticData.location.country);
            location.put("city", analyticData.location.city);
            playerSession.put("location", location);

            // Build Analytic Referer
            JSONObject referer = new JSONObject();
            referer.put("medium", analyticData.referer.medium);
            referer.put("searchTerm", analyticData.referer.search_term);
            referer.put("source", analyticData.referer.source);
            referer.put("url", analyticData.referer.url);
            playerSession.put("referer", referer);

            // Build Analytic Device
            JSONObject device = new JSONObject();
            device.put("model", analyticData.device.model);
            device.put("type", analyticData.device.type);
            device.put("vendor", analyticData.device.vendor);
            playerSession.put("device", device);

            // Build Analytic Os
            JSONObject os = new JSONObject();
            os.put("name", analyticData.os.name);
            os.put("shortname", analyticData.os.shortname);
            os.put("version", analyticData.os.version);
            playerSession.put("os", os);

            // Build Analytic Client
            JSONObject client = new JSONObject();
            client.put("type", analyticData.client.type);
            client.put("name", analyticData.client.name);
            client.put("version", analyticData.client.version);
            playerSession.put("client", client);

            dataArray.put(playerSession);

        }
        data.put("data", dataArray);


        return data;
    }

    @Override
    public JSONObject serializeProperties(AnalyticLive analyticLive) throws JSONException {
        return null;
    }


    @Override
    public List<AnalyticLive> deserialize(JSONArray data) throws JSONException {


        List<AnalyticLive> analyticLive = new ArrayList<>();
        for (Object item : data) {
            analyticLive.add(deserialize((JSONObject) item));
        }
        return analyticLive;
    }

}
