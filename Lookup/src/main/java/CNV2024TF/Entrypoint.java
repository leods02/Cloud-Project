package CNV2024TF;
import com.google.cloud.compute.v1.Instance;
import com.google.cloud.compute.v1.InstancesClient;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.util.ArrayList;

public class Entrypoint implements HttpFunction{
    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        BufferedWriter writer = response.getWriter();
        String gcpProjetID="cn2324-t2-g06";
        String zone = request.getFirstQueryParameter("zone").orElse("us-central1-a");
        ArrayList<String> ipAddresses = new ArrayList<>();
        try (InstancesClient client = InstancesClient.create()) {
            for (Instance instance : client.list(gcpProjetID, zone).iterateAll()) {
                if (instance.getStatus().compareTo("RUNNING") == 0 && instance.getName().contains("instance-group-server")) {
                    String ip = instance.getNetworkInterfaces(0).getAccessConfigs(0).getNatIP();
                    ipAddresses.add(ip);
                }
            }
        }
        writer.write(new Gson().toJson(ipAddresses.toArray()));
    }
}
