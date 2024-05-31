package CNV2024TF.PubSub;

import CNV2024TF.Firestore.FirestoreOp;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;
import java.util.Map;

public class MessageReceiveHandler implements MessageReceiver {
    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        Map<String, String> map = pubsubMessage.getAttributesMap();
        String requestId = map.get("requestId");
        String blobName = map.get("blobName");
        String bucketName = map.get("bucketName");

        Map<String, Object> logData = Map.of(
                "requestId", requestId,
                "blobName", blobName,
                "bucketName", bucketName
        );

        FirestoreOp firestoreOp = null;

        try {
            firestoreOp = new FirestoreOp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            firestoreOp.uploadToFirestore(logData, requestId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ackReplyConsumer.ack();
    }
}
