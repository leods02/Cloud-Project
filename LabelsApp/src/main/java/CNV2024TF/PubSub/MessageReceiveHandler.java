package CNV2024TF.PubSub;

import CNV2024TF.Firestore.FirestoreOp;
import CNV2024TF.Labels;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MessageReceiveHandler implements MessageReceiver {
    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        Map<String, String> map = pubsubMessage.getAttributesMap();
        String requestId = map.get("requestId");
        String blobName = map.get("blobName");
        String bucketName = map.get("bucketName");
        String uploadDate = map.get("uploadDate");

        String blobPath = "gs://" + bucketName + "/" + blobName;

        List<String> labels = null;
        try {
            labels = Labels.detectLabels(blobPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> translatedLabels = Labels.TranslateLabels(labels);


        FirestoreOp firestoreOp = null;
        try {
            firestoreOp = new FirestoreOp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            firestoreOp.uploadToFirestore(labels, translatedLabels, requestId, uploadDate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ackReplyConsumer.ack();
    }


}
