package video.api.java.sdk.infrastructure.unirest.video.serializers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import video.api.java.sdk.domain.video.models.Assets;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

import java.util.List;

public class AssetsSerializer implements JsonSerializer<Assets> {

    public JSONObject serialize(Assets assets) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("iframe", assets.iframe);
        data.put("player", assets.player);
        data.put("hls", assets.hls);
        data.put("thumbnail", assets.thumbnail);
        return data;
    }

    @Override
    public JSONObject serializeProperties(Assets assets) throws JSONException {
        return null;
    }


    @Override
    public Assets deserialize(JSONObject data) throws JSONException {

        Assets assets = new Assets();
        assets.iframe    = data.getString("iframe");
        assets.player    = data.getString("player");
        assets.hls       = data.getString("hls");
        assets.thumbnail = data.getString("thumbnail");
        return assets;
    }


    @Override
    public List deserialize(JSONArray data) throws JSONException {
        return null;
    }

}
