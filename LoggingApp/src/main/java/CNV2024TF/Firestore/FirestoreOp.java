package CNV2024TF.Firestore;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import java.util.HashMap;
import java.util.Map;

public class FirestoreOp {
    static String collection = "Logs";
    static Firestore db = null;

    public FirestoreOp() throws Exception {
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();

        FirestoreOptions options = FirestoreOptions
                .newBuilder().setDatabaseId("cn-2324-g06-db1").setCredentials(credentials).build();
        db = options.getService();
    }

    public void uploadToFirestore(Map<String, Object> dados, String requestId) throws Exception {
        CollectionReference colRef = db.collection(collection);
        DocumentReference docRef = colRef.document(requestId);

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("labels", dados);

        ApiFuture<WriteResult> res = docRef.set(data);
        WriteResult result = res.get();
        System.out.println("Info uploaded to Firestore DB");
    }
}
