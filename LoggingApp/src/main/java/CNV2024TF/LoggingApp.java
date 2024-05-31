package CNV2024TF;

import CNV2024TF.PubSub.PubSub;
import com.google.api.gax.rpc.AlreadyExistsException;

public class LoggingApp {
    public static void main(String[] args) {
        try{
            PubSub.createPubSubTopic();
            PubSub.createSubscription("cn2324-g06-tf-loggingapp");
            PubSub.createSubscription("cn2324-g06-tf-subscription");
            PubSub.createSubscriber();
        }catch(AlreadyExistsException e){
            System.out.println("subscription already exists!");
        }
        catch (Exception e){throw new RuntimeException(e);}

        while (true) {
            try {
                Thread.sleep(10000); // Espera 10 segundos
            } catch (InterruptedException e) {
                System.out.println("Program interrupted!");
                break;
            }
        }
    }
}
