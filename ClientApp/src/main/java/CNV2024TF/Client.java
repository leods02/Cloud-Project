package CNV2024TF;

import CNV2024TFEL.CNV2024TFELGrpc;
import CNV2024TFEL.CNV2024TFELOuterClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    static String cfURL="https://us-central1-cn2324-t2-g06.cloudfunctions.net/Lookup";
    private static String svcIP;
    private static int svcPort;
    private static ManagedChannel channel;
    private static CNV2024TFGrpc.CNV2024TFStub asyncStub;
    private static CNV2024TFELGrpc.CNV2024TFELBlockingStub stub;

    public static void main(String[] args) {
        try {
            ArrayList<String> ips = getVmInstanceIps();
            String svcIP = getIp(ips);

            if(svcIP.isEmpty()) return;

            svcPort = Integer.parseInt(args[0]);

            channel = ManagedChannelBuilder.forAddress(svcIP, svcPort)
                    .usePlaintext()
                    .build();
            asyncStub = CNV2024TFGrpc.newStub(channel);

            stub = CNV2024TFELGrpc.newBlockingStub(channel);

            boolean end = false;
            while (!end) {
                try {
                    int option = Menu();
                    switch (option) {
                        case 1:
                            uploadPhoto();
                            break;
                        case 2:
                            getTranslatedCharacteristics();
                            break;
                        case 3:
                            getAllFiles();
                            break;
                        case 4:
                            resizeInstanceGroup("instance-group-server");
                            break;
                        case 5:
                            resizeInstanceGroup("instance-group-labels");
                            break;
                        case 99:
                            end = true;
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println("Execution call Error  !");
                    ex.printStackTrace();
                }
            }
            read("Press enter to end", new Scanner(System.in));
        } catch (Exception ex) {
            System.out.println("Unhandled exception");
            ex.printStackTrace();
        }
    }

    private static int Menu() {
        int op;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("    MENU");
            System.out.println(" 1 - Upload Photo");
            System.out.println(" 2 - Get Translated Characteristics");
            System.out.println(" 3 - Get Files Between 2 Dates With A Specific Characteristic");
            System.out.println(" 4 - Resize the number of server instances");
            System.out.println(" 5 - Resize the number of labels app instances");
            System.out.println("99 - Exit");
            System.out.println();
            System.out.println("Choose an Option?");
            op = scan.nextInt();
        } while (!((op >= 1 && op <= 5) || op == 99));
        return op;
    }

    private static ArrayList<String> getVmInstanceIps() {
        ArrayList<String> listips = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();

            HttpGet reqGet = new HttpGet(cfURL);
            CloseableHttpResponse respGet = httpclient.execute(reqGet);
            HttpEntity entity = respGet.getEntity();
            String jstr = EntityUtils.toString(entity);

            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            listips = new Gson().fromJson(jstr, listType);

            httpclient.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listips;
    }

    private static String getIp(ArrayList<String> ips) throws IOException {
        boolean stop = false;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!stop) {
            System.out.println("Escolha um IP dentro dos seguintes:");
            for (String aux : ips)
                System.out.println("- " + aux);

            svcIP = reader.readLine();

            while (!(ips.contains(svcIP))) {
                System.out.println("O IP inserido não existe na lista! Pretende introduzir outro IP, repetir o processo de Lookup, ou sair?");
                System.out.println("1 - Outro IP");
                System.out.println("2 - Lookup");
                System.out.println("3 - Sair");

                int opt = Integer.parseInt(reader.readLine().trim());

                switch (opt) {
                    case 1:
                        System.out.println("Insira outro IP:");
                        svcIP = reader.readLine();
                        break;
                    case 2:
                        break;
                    case 3:
                        return "";
                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
                        break;
                }

                if (opt == 2) {
                    break;
                }
            }

            if (ips.contains(svcIP)) {
                stop = true;
            }
        }

        return svcIP;
    }

    private static String read(String msg, Scanner input) {
        System.out.println(msg);
        return input.nextLine();
    }

    private static void uploadPhoto() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path of the image to upload:");
        String filePath = scanner.nextLine();
        System.out.println("Enter the blob's name: ");
        String blobName = scanner.nextLine();


        FileInputStream fileInputStream = new FileInputStream(filePath);
        StreamObserver<CNV2024TFOuterClass.Block> requestObserver = asyncStub.uploadPhoto(new StreamObserver<CNV2024TFOuterClass.BlobIdentifier>() {
            @Override
            public void onNext(CNV2024TFOuterClass.BlobIdentifier value) {
                System.out.println("Image uploaded successfully. Blob Identifier: " + value.getBlobIdent());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Image upload completed.");
            }
        });

        requestObserver.onNext(CNV2024TFOuterClass.Block.newBuilder()
                .setBlobName(blobName+".png")
                .setDataType("start")
                .build());

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            CNV2024TFOuterClass.Block block = CNV2024TFOuterClass.Block.newBuilder()
                    .setBlobName(blobName+".png")
                    .setData(com.google.protobuf.ByteString.copyFrom(buffer, 0, bytesRead))
                    .setDataType("data")
                    .build();
            requestObserver.onNext(block);
        }

        requestObserver.onNext(CNV2024TFOuterClass.Block.newBuilder()
                .setDataType("end")
                .build());

        requestObserver.onCompleted();

        fileInputStream.close();
    }

    private static void resizeInstanceGroup(String instance){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new size: ");
        int size = Integer.parseInt(scanner.nextLine());

        CNV2024TFELOuterClass.InstanceName instanceName = CNV2024TFELOuterClass.InstanceName.newBuilder()
                .setName(instance)
                .setNewSize(size)
                .build();

        try {
            Empty response = stub.resizeInstanceGroup(instanceName);
            System.out.println("Instance group resized successfully.");
        } catch (Exception e) {
            System.err.println("Failed to resize instance group: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void getTranslatedCharacteristics() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the blob's identifier: ");
        String blobIdentifier = scanner.nextLine();

        CNV2024TFOuterClass.BlobIdentifier request = CNV2024TFOuterClass.BlobIdentifier.newBuilder()
                .setBlobIdent(blobIdentifier)
                .build();

        final boolean[] first = {true};

        StreamObserver<CNV2024TFOuterClass.Characteristics> responseObserver = new StreamObserver<CNV2024TFOuterClass.Characteristics>() {
            @Override
            public void onNext(CNV2024TFOuterClass.Characteristics characteristics) {
                String name = characteristics.getName();

                if(!name.isEmpty())System.out.println("Label: " + name);

                if(first[0]) {
                    String date = characteristics.getDate();

                    System.out.println("Date: " + date);
                    first[0] = false;
                }
            }


            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
            }
        };

        asyncStub.getTranslatedCharacteristics(request, responseObserver);
    }

    public static void getAllFiles(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first date (yyyy-MM-dd):");
        String date1 = scanner.nextLine();
        System.out.println("Enter the second date (yyyy-MM-dd): ");
        String date2 = scanner.nextLine();
        System.out.println("Enter the characteristic: ");
        String characteristic = scanner.nextLine();

        CNV2024TFOuterClass.DateAndCharacteristic request = CNV2024TFOuterClass.DateAndCharacteristic.newBuilder()
                .setStartDate(date1)
                .setEndDate(date2)
                .setFileCharacteristic(characteristic)
                .build();

        StreamObserver<CNV2024TFOuterClass.File> responseObserver = new StreamObserver<CNV2024TFOuterClass.File>() {
            @Override
            public void onNext(CNV2024TFOuterClass.File file) {
                System.out.println("File: " + file.getBlobName());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Finished retrieving files.");
            }
        };

        asyncStub.getAllFiles(request, responseObserver);
    }
}
