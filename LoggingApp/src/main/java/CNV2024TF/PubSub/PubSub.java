package CNV2024TF.PubSub;

import com.google.api.core.ApiFuture;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.common.collect.ImmutableMap;
import com.google.pubsub.v1.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PubSub {
    static String PROJECT_ID = "cn2324-t2-g06";
    static TopicAdminClient topicAdmin;
    static String PubSubTopicName = "cn2324-g06-tf";

    public static void createPubSubTopic() throws IOException, InterruptedException {
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
    public static boolean verifyIfTopicExists(String topicName){
        TopicAdminClient.ListTopicsPagedResponse res = topicAdmin.listTopics(ProjectName.of(PROJECT_ID));
        boolean haveTopic = false;
        for (Topic top : res.iterateAll()) {
            String name = top.getName().split("/")[3];
            if(name.equals(topicName)) {haveTopic=true; break;}
        }
        return haveTopic;
    }
    public static void createSubscription(String subscriptionName) {
        TopicName tName = TopicName.ofProjectTopicName(PROJECT_ID, PubSubTopicName);
        SubscriptionName subsName = SubscriptionName.of(PROJECT_ID, subscriptionName);
        SubscriptionAdminClient subscriptionAdminClient = null;
        try {
            subscriptionAdminClient = SubscriptionAdminClient.create();
            PushConfig pconfig = PushConfig.getDefaultInstance();
            subscriptionAdminClient.createSubscription(subsName, tName, pconfig, 0);
        } catch (Exception e) {
            // Tratar exceção
        } finally {
            if (subscriptionAdminClient != null) {
                subscriptionAdminClient.close(); // Fechar o cliente aqui
            }
        }
        System.out.println("Subscription created to " + subscriptionName);
    }
    public static void createSubscriber(){
        String subscriptionName = "cn2324-g06-tf-loggingapp";
        ProjectSubscriptionName projSubscriptionName = ProjectSubscriptionName.of(PROJECT_ID, subscriptionName);
        ExecutorProvider executorProvider = InstantiatingExecutorProvider
                .newBuilder()
                .setExecutorThreadCount(1) // um só thread no handler
                .build();
        Subscriber subscriber =
                Subscriber.newBuilder(projSubscriptionName, new MessageReceiveHandler())
                        .setExecutorProvider(executorProvider)
                        .build();
        subscriber.startAsync().awaitRunning();
        System.out.println("Subscriber created to " + subscriptionName);
        //subscriber.awaitTerminated();
    }
}

