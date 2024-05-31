package CNV2024TF.PubSub;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.common.collect.ImmutableMap;
import com.google.pubsub.v1.ProjectName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.Topic;
import com.google.pubsub.v1.TopicName;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PubSub {
    static String PROJECT_ID = "cn2324-t2-g06";
    static TopicAdminClient topicAdmin;
    static String PubSubTopicName = "cn2324-g06-tf";

    public static void createPubSubTopic() throws InterruptedException, IOException {
        try{
            topicAdmin = TopicAdminClient.create();
        } catch(Exception e){
            topicAdmin.shutdown();
            topicAdmin.awaitTermination(5, TimeUnit.SECONDS);
            topicAdmin = TopicAdminClient.create();
        }
        if(!verifyIfTopicExists(PubSubTopicName)){
            System.out.println("Creating topic PUBSUB");
            TopicName tName=TopicName.ofProjectTopicName(PROJECT_ID, PubSubTopicName);
            Topic topic=topicAdmin.createTopic(tName);
        }
        else System.out.println("Pub/Sub topic already exists");
    }
    public static void stopPubSubTopic(){
        topicAdmin.close();
        System.out.println("Topic Admin close!");
    }
    public static boolean verifyIfTopicExists(String topicName){
        TopicAdminClient.ListTopicsPagedResponse res = topicAdmin.listTopics(ProjectName.of(PROJECT_ID));
        boolean haveTopic = false;
        for (Topic top : res.iterateAll()) {
            String name = top.getName().split("/")[3];
            if(name.equals(topicName)) {haveTopic=true; break;}
        }
        return haveTopic;
    }

    public static void publishMessage(String requestId, String bucketName, String blobName, String uploadDate) throws ExecutionException, InterruptedException, IOException {
        TopicName tName = TopicName.ofProjectTopicName(PROJECT_ID, PubSubTopicName);
        Publisher publisher = Publisher.newBuilder(tName).build();

        PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                .putAllAttributes(ImmutableMap.of("requestId", requestId, "bucketName", bucketName,"blobName", blobName, "uploadDate", uploadDate))
                .build();
        ApiFuture<String> future = publisher.publish(pubsubMessage);
        String msgID = future.get();
        System.out.println("Message Published with ID=" + msgID);
        publisher.shutdown();
    }
}
