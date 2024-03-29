package video.api.java.sdk.infrastructure.unirest.video.serializers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import video.api.java.sdk.domain.video.models.Encoding;
import video.api.java.sdk.domain.video.models.Quality;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.List;

public class EncodingSerializer implements JsonSerializer<Encoding> {

    @Override
    public Encoding deserialize(JSONObject data) {
        QualitySerializer  qualitySerializer  = new QualitySerializer();
        MetadataSerializer metadataSerializer = new MetadataSerializer();
        Encoding           encoding           = new Encoding();
        encoding.playable  = data.getBoolean("playable");
        encoding.qualities = new ArrayList<Quality>();

        for (Object item : data.getJSONArray("qualities")) {
            encoding.qualities.add(qualitySerializer.deserialize((JSONObject) item));
        }

        encoding.metadata = metadataSerializer.deserialize(data.getJSONObject("metadata"));
        return encoding;
    }

    @Override
    public List<Encoding> deserialize(JSONArray data) throws JSONException {
        return null;
    }

    @Override
    public JSONObject serialize(Encoding encoding) {

        JSONObject         data               = new JSONObject();
        QualitySerializer  qualitySerializer  = new QualitySerializer();
        MetadataSerializer metadataSerializer = new MetadataSerializer();
        data.put("playable", encoding.playable);
        data.put("qualities", qualitySerializer.serialize(encoding.qualities));
        data.put("metadata", metadataSerializer.serialize(encoding.metadata));

        return data;
    }

    @Override
    public JSONObject serializeProperties(Encoding encoding) throws JSONException {
        return null;
    }


}
