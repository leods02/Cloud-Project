package CNV2024TF.FirestoreOp;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirestoreOp {
    static String collection = "cn-2324-g06-col";
    static Firestore db = null;

    public FirestoreOp() throws Exception {
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();

        FirestoreOptions options = FirestoreOptions
                .newBuilder().setDatabaseId("cn-2324-g06-db1").setCredentials(credentials).build();
        db = options.getService();
    }

    public Map<String, Object> getResultsWithId(String Id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(collection).document(Id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.getData();
    }

    public QuerySnapshot getResultsWithDatesAndCharacteristic(Date date1, Date date2, String characteristic) throws ExecutionException, InterruptedException {
        CollectionReference col = db.collection(collection);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = dateFormat.format(date1);
        String endDateStr = dateFormat.format(date2);


        Query query = col
                .whereGreaterThanOrEqualTo("date", startDateStr)
                .whereLessThanOrEqualTo("date", endDateStr)
                .whereArrayContains("labels", characteristic);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        return querySnapshot.get();
    }
}
