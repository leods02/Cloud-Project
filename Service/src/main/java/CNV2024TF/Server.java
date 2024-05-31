package CNV2024TF;

import CNV2024TF.FirestoreOp.FirestoreOp;
import CNV2024TF.PubSub.PubSub;
import CNV2024TFEL.CNV2024TFELGrpc;
import CNV2024TFEL.CNV2024TFELOuterClass;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.storage.*;
import com.google.protobuf.Empty;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import com.google.cloud.compute.v1.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Server extends CNV2024TFGrpc.CNV2024TFImplBase {

    static String BUCKET_NAME = "cn-2324-g06-tf";
    static String PROJECT_ID = "cn2324-t2-g06";
    static String ZONE = "us-central1-a";
    private static int svcPort = 8000;
    private final Storage storage;


    public Server() {
        // Initialize Google Cloud Storage client
        this.storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
    }

    public static void main(String[] args) {
        try {
            if (args.length > 0) svcPort = Integer.parseInt(args[0]);
            io.grpc.Server svc = ServerBuilder.forPort(svcPort)
                    .addService(new Server())
                    .addService(new ServerEL())
                    .build();
            svc.start();
            System.out.println("Server started on port " + svcPort);

            svc.awaitTermination();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public StreamObserver<CNV2024TF.CNV2024TFOuterClass.Block> uploadPhoto(StreamObserver<CNV2024TF.CNV2024TFOuterClass.BlobIdentifier> responseObserver) {
        return new StreamObserver<CNV2024TF.CNV2024TFOuterClass.Block>() {

            public OffsetDateTime uploadDate;
            private BlobId blobId;
            private WritableByteChannel writableChannel;

            @Override
            public void onNext(CNV2024TF.CNV2024TFOuterClass.Block block) {
                if ("start".equals(block.getDataType())) {
                    String blobName = block.getBlobName();
                    blobId = BlobId.of(BUCKET_NAME, blobName);
                    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
                    writableChannel = storage.writer(blobInfo);
                } else if ("data".equals(block.getDataType())) {
                    try {
                        writableChannel.write(ByteBuffer.wrap(block.getData().toByteArray()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                try {
                    writableChannel.close();
                    Blob blob = storage.get(blobId);
                    Acl.Entity aclEnt = Acl.User.ofAllUsers();
                    Acl.Role role = Acl.Role.READER;
                    Acl acl = Acl.newBuilder(aclEnt, role).build();
                    blob.createAcl(acl);

                    CNV2024TF.CNV2024TFOuterClass.BlobIdentifier blobIdentifier = CNV2024TF.CNV2024TFOuterClass.BlobIdentifier.newBuilder()
                            .setBlobIdent(blobId.getName())
                            .build();
                    responseObserver.onNext(blobIdentifier);
                    responseObserver.onCompleted();

                    PubSub.createPubSubTopic();

                    uploadDate = blob.getCreateTimeOffsetDateTime();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    String formattedDate = uploadDate.format(formatter);

                    String requestId = generateRequestId(blobId.getName(), BUCKET_NAME);

                    PubSub.publishMessage(requestId, BUCKET_NAME, blob.getName(), formattedDate);

                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private String generateRequestId(String blobName, String bucketName){
        return bucketName + "_" + blobName;
    }

    @Override
    public void getTranslatedCharacteristics(CNV2024TF.CNV2024TFOuterClass.BlobIdentifier request, StreamObserver<CNV2024TF.CNV2024TFOuterClass.Characteristics> responseObserver) {

        String requestId = BUCKET_NAME + "_" + request.getBlobIdent();

        Map<String, Object> results = null;
        FirestoreOp firestoreOp = null;
        try {
            firestoreOp = new FirestoreOp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            results = firestoreOp.getResultsWithId(requestId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return;
        }

        for (Map.Entry<String, Object> entry : results.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            CNV2024TF.CNV2024TFOuterClass.Characteristics.Builder characteristicsBuilder;
            if ("labels".equals(key) || "originalLabels".equals(key)) {
                List<String> labelsList = (List<String>) value;
                for (String label : labelsList) {
                    characteristicsBuilder = CNV2024TF.CNV2024TFOuterClass.Characteristics.newBuilder()
                            .setName(label);
                    responseObserver.onNext(characteristicsBuilder.build());
                }
            }
            if("date".equals(key)){
                characteristicsBuilder = CNV2024TF.CNV2024TFOuterClass.Characteristics.newBuilder()
                        .setDate(value.toString());
                responseObserver.onNext(characteristicsBuilder.build());
            }
        }

        responseObserver.onCompleted();
    }

    @Override
    public void getAllFiles(CNV2024TFOuterClass.DateAndCharacteristic request, StreamObserver<CNV2024TFOuterClass.File> responseObserver) {
        String startDateStr = request.getStartDate();
        String endDateStr = request.getEndDate();
        String fileCharacteristic = request.getFileCharacteristic();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        Date endDate;

        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);
        } catch (Exception e) {
            responseObserver.onError(e);
            return;
        }


        FirestoreOp firestoreOp;
        try {
            firestoreOp = new FirestoreOp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        QuerySnapshot q;
        try {
            q = firestoreOp.getResultsWithDatesAndCharacteristic(startDate, endDate, fileCharacteristic);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (DocumentSnapshot doc : q.getDocuments()) {

            CNV2024TFOuterClass.File file = CNV2024TFOuterClass.File.newBuilder()
                    .setBlobName(doc.getId())
                    .build();
            responseObserver.onNext(file);
        }

        responseObserver.onCompleted();
    }
       public static class ServerEL extends CNV2024TFELGrpc.CNV2024TFELImplBase{
         @Override
         public void resizeInstanceGroup(CNV2024TFELOuterClass.InstanceName request, StreamObserver<Empty> responseObserver) {
             String instanceGroupName = request.getName();
             int newSize = request.getNewSize();

             try {
                 resizeManagedInstanceGroup(instanceGroupName, newSize);

                 responseObserver.onNext(Empty.getDefaultInstance());
                 responseObserver.onCompleted();
             } catch (IOException | InterruptedException | ExecutionException e) {
                 responseObserver.onError(e);
             }
         }

         public void resizeManagedInstanceGroup(String instanceGroupName, int newSize) throws IOException, InterruptedException, ExecutionException {
             System.out.println("================== Resizing instance group");
             InstanceGroupManagersClient managersClient = InstanceGroupManagersClient.create();
             OperationFuture<Operation, Operation> result = managersClient.resizeAsync(
                     PROJECT_ID,
                     ZONE,
                     instanceGroupName,
                     newSize
             );
             Operation oper = result.get();
             System.out.println("Resizing with status " + oper.getStatus());
         }
    }
}
