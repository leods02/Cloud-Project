package CNV2024TF;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: CNV2024TF.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CNV2024TFGrpc {

  private CNV2024TFGrpc() {}

  public static final java.lang.String SERVICE_NAME = "CNV2024TF.CNV2024TF";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<CNV2024TF.CNV2024TFOuterClass.Block,
      CNV2024TF.CNV2024TFOuterClass.BlobIdentifier> getUploadPhotoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "uploadPhoto",
      requestType = CNV2024TF.CNV2024TFOuterClass.Block.class,
      responseType = CNV2024TF.CNV2024TFOuterClass.BlobIdentifier.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<CNV2024TF.CNV2024TFOuterClass.Block,
      CNV2024TF.CNV2024TFOuterClass.BlobIdentifier> getUploadPhotoMethod() {
    io.grpc.MethodDescriptor<CNV2024TF.CNV2024TFOuterClass.Block, CNV2024TF.CNV2024TFOuterClass.BlobIdentifier> getUploadPhotoMethod;
    if ((getUploadPhotoMethod = CNV2024TFGrpc.getUploadPhotoMethod) == null) {
      synchronized (CNV2024TFGrpc.class) {
        if ((getUploadPhotoMethod = CNV2024TFGrpc.getUploadPhotoMethod) == null) {
          CNV2024TFGrpc.getUploadPhotoMethod = getUploadPhotoMethod =
              io.grpc.MethodDescriptor.<CNV2024TF.CNV2024TFOuterClass.Block, CNV2024TF.CNV2024TFOuterClass.BlobIdentifier>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "uploadPhoto"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CNV2024TF.CNV2024TFOuterClass.Block.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CNV2024TF.CNV2024TFOuterClass.BlobIdentifier.getDefaultInstance()))
              .setSchemaDescriptor(new CNV2024TFMethodDescriptorSupplier("uploadPhoto"))
              .build();
        }
      }
    }
    return getUploadPhotoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<CNV2024TF.CNV2024TFOuterClass.BlobIdentifier,
      CNV2024TF.CNV2024TFOuterClass.Characteristics> getGetTranslatedCharacteristicsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getTranslatedCharacteristics",
      requestType = CNV2024TF.CNV2024TFOuterClass.BlobIdentifier.class,
      responseType = CNV2024TF.CNV2024TFOuterClass.Characteristics.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<CNV2024TF.CNV2024TFOuterClass.BlobIdentifier,
      CNV2024TF.CNV2024TFOuterClass.Characteristics> getGetTranslatedCharacteristicsMethod() {
    io.grpc.MethodDescriptor<CNV2024TF.CNV2024TFOuterClass.BlobIdentifier, CNV2024TF.CNV2024TFOuterClass.Characteristics> getGetTranslatedCharacteristicsMethod;
    if ((getGetTranslatedCharacteristicsMethod = CNV2024TFGrpc.getGetTranslatedCharacteristicsMethod) == null) {
      synchronized (CNV2024TFGrpc.class) {
        if ((getGetTranslatedCharacteristicsMethod = CNV2024TFGrpc.getGetTranslatedCharacteristicsMethod) == null) {
          CNV2024TFGrpc.getGetTranslatedCharacteristicsMethod = getGetTranslatedCharacteristicsMethod =
              io.grpc.MethodDescriptor.<CNV2024TF.CNV2024TFOuterClass.BlobIdentifier, CNV2024TF.CNV2024TFOuterClass.Characteristics>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getTranslatedCharacteristics"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CNV2024TF.CNV2024TFOuterClass.BlobIdentifier.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CNV2024TF.CNV2024TFOuterClass.Characteristics.getDefaultInstance()))
              .setSchemaDescriptor(new CNV2024TFMethodDescriptorSupplier("getTranslatedCharacteristics"))
              .build();
        }
      }
    }
    return getGetTranslatedCharacteristicsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<CNV2024TF.CNV2024TFOuterClass.DateAndCharacteristic,
      CNV2024TF.CNV2024TFOuterClass.File> getGetAllFilesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAllFiles",
      requestType = CNV2024TF.CNV2024TFOuterClass.DateAndCharacteristic.class,
      responseType = CNV2024TF.CNV2024TFOuterClass.File.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<CNV2024TF.CNV2024TFOuterClass.DateAndCharacteristic,
      CNV2024TF.CNV2024TFOuterClass.File> getGetAllFilesMethod() {
    io.grpc.MethodDescriptor<CNV2024TF.CNV2024TFOuterClass.DateAndCharacteristic, CNV2024TF.CNV2024TFOuterClass.File> getGetAllFilesMethod;
    if ((getGetAllFilesMethod = CNV2024TFGrpc.getGetAllFilesMethod) == null) {
      synchronized (CNV2024TFGrpc.class) {
        if ((getGetAllFilesMethod = CNV2024TFGrpc.getGetAllFilesMethod) == null) {
          CNV2024TFGrpc.getGetAllFilesMethod = getGetAllFilesMethod =
              io.grpc.MethodDescriptor.<CNV2024TF.CNV2024TFOuterClass.DateAndCharacteristic, CNV2024TF.CNV2024TFOuterClass.File>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getAllFiles"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CNV2024TF.CNV2024TFOuterClass.DateAndCharacteristic.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CNV2024TF.CNV2024TFOuterClass.File.getDefaultInstance()))
              .setSchemaDescriptor(new CNV2024TFMethodDescriptorSupplier("getAllFiles"))
              .build();
        }
      }
    }
    return getGetAllFilesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CNV2024TFStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CNV2024TFStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CNV2024TFStub>() {
        @java.lang.Override
        public CNV2024TFStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CNV2024TFStub(channel, callOptions);
        }
      };
    return CNV2024TFStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CNV2024TFBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CNV2024TFBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CNV2024TFBlockingStub>() {
        @java.lang.Override
        public CNV2024TFBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CNV2024TFBlockingStub(channel, callOptions);
        }
      };
    return CNV2024TFBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CNV2024TFFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CNV2024TFFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CNV2024TFFutureStub>() {
        @java.lang.Override
        public CNV2024TFFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CNV2024TFFutureStub(channel, callOptions);
        }
      };
    return CNV2024TFFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<CNV2024TF.CNV2024TFOuterClass.Block> uploadPhoto(
        io.grpc.stub.StreamObserver<CNV2024TF.CNV2024TFOuterClass.BlobIdentifier> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getUploadPhotoMethod(), responseObserver);
    }

    /**
     */
    default void getTranslatedCharacteristics(CNV2024TF.CNV2024TFOuterClass.BlobIdentifier request,
        io.grpc.stub.StreamObserver<CNV2024TF.CNV2024TFOuterClass.Characteristics> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTranslatedCharacteristicsMethod(), responseObserver);
    }

    /**
     */
    default void getAllFiles(CNV2024TF.CNV2024TFOuterClass.DateAndCharacteristic request,
        io.grpc.stub.StreamObserver<CNV2024TF.CNV2024TFOuterClass.File> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllFilesMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CNV2024TF.
   */
  public static abstract class CNV2024TFImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CNV2024TFGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CNV2024TF.
   */
  public static final class CNV2024TFStub
      extends io.grpc.stub.AbstractAsyncStub<CNV2024TFStub> {
    private CNV2024TFStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CNV2024TFStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CNV2024TFStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<CNV2024TF.CNV2024TFOuterClass.Block> uploadPhoto(
        io.grpc.stub.StreamObserver<CNV2024TF.CNV2024TFOuterClass.BlobIdentifier> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getUploadPhotoMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void getTranslatedCharacteristics(CNV2024TF.CNV2024TFOuterClass.BlobIdentifier request,
        io.grpc.stub.StreamObserver<CNV2024TF.CNV2024TFOuterClass.Characteristics> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetTranslatedCharacteristicsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllFiles(CNV2024TF.CNV2024TFOuterClass.DateAndCharacteristic request,
        io.grpc.stub.StreamObserver<CNV2024TF.CNV2024TFOuterClass.File> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetAllFilesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CNV2024TF.
   */
  public static final class CNV2024TFBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CNV2024TFBlockingStub> {
    private CNV2024TFBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CNV2024TFBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CNV2024TFBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<CNV2024TF.CNV2024TFOuterClass.Characteristics> getTranslatedCharacteristics(
        CNV2024TF.CNV2024TFOuterClass.BlobIdentifier request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetTranslatedCharacteristicsMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<CNV2024TF.CNV2024TFOuterClass.File> getAllFiles(
        CNV2024TF.CNV2024TFOuterClass.DateAndCharacteristic request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetAllFilesMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CNV2024TF.
   */
  public static final class CNV2024TFFutureStub
      extends io.grpc.stub.AbstractFutureStub<CNV2024TFFutureStub> {
    private CNV2024TFFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CNV2024TFFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CNV2024TFFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_TRANSLATED_CHARACTERISTICS = 0;
  private static final int METHODID_GET_ALL_FILES = 1;
  private static final int METHODID_UPLOAD_PHOTO = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TRANSLATED_CHARACTERISTICS:
          serviceImpl.getTranslatedCharacteristics((CNV2024TF.CNV2024TFOuterClass.BlobIdentifier) request,
              (io.grpc.stub.StreamObserver<CNV2024TF.CNV2024TFOuterClass.Characteristics>) responseObserver);
          break;
        case METHODID_GET_ALL_FILES:
          serviceImpl.getAllFiles((CNV2024TF.CNV2024TFOuterClass.DateAndCharacteristic) request,
              (io.grpc.stub.StreamObserver<CNV2024TF.CNV2024TFOuterClass.File>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_PHOTO:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.uploadPhoto(
              (io.grpc.stub.StreamObserver<CNV2024TF.CNV2024TFOuterClass.BlobIdentifier>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getUploadPhotoMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              CNV2024TF.CNV2024TFOuterClass.Block,
              CNV2024TF.CNV2024TFOuterClass.BlobIdentifier>(
                service, METHODID_UPLOAD_PHOTO)))
        .addMethod(
          getGetTranslatedCharacteristicsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              CNV2024TF.CNV2024TFOuterClass.BlobIdentifier,
              CNV2024TF.CNV2024TFOuterClass.Characteristics>(
                service, METHODID_GET_TRANSLATED_CHARACTERISTICS)))
        .addMethod(
          getGetAllFilesMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              CNV2024TF.CNV2024TFOuterClass.DateAndCharacteristic,
              CNV2024TF.CNV2024TFOuterClass.File>(
                service, METHODID_GET_ALL_FILES)))
        .build();
  }

  private static abstract class CNV2024TFBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CNV2024TFBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return CNV2024TF.CNV2024TFOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CNV2024TF");
    }
  }

  private static final class CNV2024TFFileDescriptorSupplier
      extends CNV2024TFBaseDescriptorSupplier {
    CNV2024TFFileDescriptorSupplier() {}
  }

  private static final class CNV2024TFMethodDescriptorSupplier
      extends CNV2024TFBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CNV2024TFMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CNV2024TFGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CNV2024TFFileDescriptorSupplier())
              .addMethod(getUploadPhotoMethod())
              .addMethod(getGetTranslatedCharacteristicsMethod())
              .addMethod(getGetAllFilesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
