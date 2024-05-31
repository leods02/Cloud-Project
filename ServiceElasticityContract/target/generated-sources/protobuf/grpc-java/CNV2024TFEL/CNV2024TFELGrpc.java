package CNV2024TFEL;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: CNV2024TFEL.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CNV2024TFELGrpc {

  private CNV2024TFELGrpc() {}

  public static final java.lang.String SERVICE_NAME = "CNV2024TFEL.CNV2024TFEL";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<CNV2024TFEL.CNV2024TFELOuterClass.InstanceName,
      com.google.protobuf.Empty> getResizeInstanceGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "resizeInstanceGroup",
      requestType = CNV2024TFEL.CNV2024TFELOuterClass.InstanceName.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<CNV2024TFEL.CNV2024TFELOuterClass.InstanceName,
      com.google.protobuf.Empty> getResizeInstanceGroupMethod() {
    io.grpc.MethodDescriptor<CNV2024TFEL.CNV2024TFELOuterClass.InstanceName, com.google.protobuf.Empty> getResizeInstanceGroupMethod;
    if ((getResizeInstanceGroupMethod = CNV2024TFELGrpc.getResizeInstanceGroupMethod) == null) {
      synchronized (CNV2024TFELGrpc.class) {
        if ((getResizeInstanceGroupMethod = CNV2024TFELGrpc.getResizeInstanceGroupMethod) == null) {
          CNV2024TFELGrpc.getResizeInstanceGroupMethod = getResizeInstanceGroupMethod =
              io.grpc.MethodDescriptor.<CNV2024TFEL.CNV2024TFELOuterClass.InstanceName, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "resizeInstanceGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CNV2024TFEL.CNV2024TFELOuterClass.InstanceName.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new CNV2024TFELMethodDescriptorSupplier("resizeInstanceGroup"))
              .build();
        }
      }
    }
    return getResizeInstanceGroupMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CNV2024TFELStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CNV2024TFELStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CNV2024TFELStub>() {
        @java.lang.Override
        public CNV2024TFELStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CNV2024TFELStub(channel, callOptions);
        }
      };
    return CNV2024TFELStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CNV2024TFELBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CNV2024TFELBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CNV2024TFELBlockingStub>() {
        @java.lang.Override
        public CNV2024TFELBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CNV2024TFELBlockingStub(channel, callOptions);
        }
      };
    return CNV2024TFELBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CNV2024TFELFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CNV2024TFELFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CNV2024TFELFutureStub>() {
        @java.lang.Override
        public CNV2024TFELFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CNV2024TFELFutureStub(channel, callOptions);
        }
      };
    return CNV2024TFELFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void resizeInstanceGroup(CNV2024TFEL.CNV2024TFELOuterClass.InstanceName request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getResizeInstanceGroupMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CNV2024TFEL.
   */
  public static abstract class CNV2024TFELImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CNV2024TFELGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CNV2024TFEL.
   */
  public static final class CNV2024TFELStub
      extends io.grpc.stub.AbstractAsyncStub<CNV2024TFELStub> {
    private CNV2024TFELStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CNV2024TFELStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CNV2024TFELStub(channel, callOptions);
    }

    /**
     */
    public void resizeInstanceGroup(CNV2024TFEL.CNV2024TFELOuterClass.InstanceName request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getResizeInstanceGroupMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CNV2024TFEL.
   */
  public static final class CNV2024TFELBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CNV2024TFELBlockingStub> {
    private CNV2024TFELBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CNV2024TFELBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CNV2024TFELBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty resizeInstanceGroup(CNV2024TFEL.CNV2024TFELOuterClass.InstanceName request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getResizeInstanceGroupMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CNV2024TFEL.
   */
  public static final class CNV2024TFELFutureStub
      extends io.grpc.stub.AbstractFutureStub<CNV2024TFELFutureStub> {
    private CNV2024TFELFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CNV2024TFELFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CNV2024TFELFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> resizeInstanceGroup(
        CNV2024TFEL.CNV2024TFELOuterClass.InstanceName request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getResizeInstanceGroupMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RESIZE_INSTANCE_GROUP = 0;

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
        case METHODID_RESIZE_INSTANCE_GROUP:
          serviceImpl.resizeInstanceGroup((CNV2024TFEL.CNV2024TFELOuterClass.InstanceName) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getResizeInstanceGroupMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              CNV2024TFEL.CNV2024TFELOuterClass.InstanceName,
              com.google.protobuf.Empty>(
                service, METHODID_RESIZE_INSTANCE_GROUP)))
        .build();
  }

  private static abstract class CNV2024TFELBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CNV2024TFELBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return CNV2024TFEL.CNV2024TFELOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CNV2024TFEL");
    }
  }

  private static final class CNV2024TFELFileDescriptorSupplier
      extends CNV2024TFELBaseDescriptorSupplier {
    CNV2024TFELFileDescriptorSupplier() {}
  }

  private static final class CNV2024TFELMethodDescriptorSupplier
      extends CNV2024TFELBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CNV2024TFELMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (CNV2024TFELGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CNV2024TFELFileDescriptorSupplier())
              .addMethod(getResizeInstanceGroupMethod())
              .build();
        }
      }
    }
    return result;
  }
}
