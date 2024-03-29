package video.api.java.sdk.infrastructure.unirest.video.serializers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import video.api.java.sdk.domain.video.models.Ingest;
import video.api.java.sdk.domain.video.models.ReceivedByte;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.List;

public class IngestSerializer implements JsonSerializer<Ingest> {

    public Ingest deserialize(JSONObject data) {
        try {
            Ingest ingest = new Ingest();
            ingest.status        = data.getString("status");
            ingest.filesize      = data.getInt("filesize");
            ingest.receivedBytes = new ArrayList<ReceivedByte>();
            ReceivedByteSerializer receivedByteSerializer = new ReceivedByteSerializer();
            for (Object item : data.getJSONArray("receivedBytes")) {
                ingest.receivedBytes.add(receivedByteSerializer.deserialize((JSONObject) item));
            }
            return ingest;
        } catch (JSONException e) {
            return new Ingest();

        }
    }

    @Override
    public List<Ingest> deserialize(JSONArray data) throws JSONException {
        return null;
    }

    public JSONObject serialize(Ingest ingest) {
        ReceivedByteSerializer receivedByteSerializer = new ReceivedByteSerializer();

        JSONObject data = new JSONObject();
        data.put("status", ingest.status);
        data.put("filesize", ingest.filesize);
        JSONArray tabs = receivedByteSerializer.serialize(ingest.receivedBytes);
        data.put("receivedBytes", tabs);

        return data;

    }

    @Override
    public JSONObject serializeProperties(Ingest ingest) throws JSONException {
        return null;
    }


}





