package com.xmniao.thrift.busine.live;
/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */

import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.server.AbstractNonblockingServer.AsyncFrameBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;

public class DebitcardService {

  public interface Iface {

    /**
     * 兑换商家储值卡
     * 
     * @param paramMap
     */
    public ResponseData exchangeDebitcard(Map<String,String> paramMap) throws FailureException, org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void exchangeDebitcard(Map<String,String> paramMap, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public ResponseData exchangeDebitcard(Map<String,String> paramMap) throws FailureException, org.apache.thrift.TException
    {
      send_exchangeDebitcard(paramMap);
      return recv_exchangeDebitcard();
    }

    public void send_exchangeDebitcard(Map<String,String> paramMap) throws org.apache.thrift.TException
    {
      exchangeDebitcard_args args = new exchangeDebitcard_args();
      args.setParamMap(paramMap);
      sendBase("exchangeDebitcard", args);
    }

    public ResponseData recv_exchangeDebitcard() throws FailureException, org.apache.thrift.TException
    {
      exchangeDebitcard_result result = new exchangeDebitcard_result();
      receiveBase(result, "exchangeDebitcard");
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.qe != null) {
        throw result.qe;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "exchangeDebitcard failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void exchangeDebitcard(Map<String,String> paramMap, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException {
      checkReady();
      exchangeDebitcard_call method_call = new exchangeDebitcard_call(paramMap, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class exchangeDebitcard_call extends org.apache.thrift.async.TAsyncMethodCall {
      private Map<String,String> paramMap;
      public exchangeDebitcard_call(Map<String,String> paramMap, org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.paramMap = paramMap;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("exchangeDebitcard", org.apache.thrift.protocol.TMessageType.CALL, 0));
        exchangeDebitcard_args args = new exchangeDebitcard_args();
        args.setParamMap(paramMap);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public ResponseData getResult() throws FailureException, org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_exchangeDebitcard();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("exchangeDebitcard", new exchangeDebitcard());
      return processMap;
    }

    public static class exchangeDebitcard<I extends Iface> extends org.apache.thrift.ProcessFunction<I, exchangeDebitcard_args> {
      public exchangeDebitcard() {
        super("exchangeDebitcard");
      }

      public exchangeDebitcard_args getEmptyArgsInstance() {
        return new exchangeDebitcard_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public exchangeDebitcard_result getResult(I iface, exchangeDebitcard_args args) throws org.apache.thrift.TException {
        exchangeDebitcard_result result = new exchangeDebitcard_result();
        try {
          result.success = iface.exchangeDebitcard(args.paramMap);
        } catch (FailureException qe) {
          result.qe = qe;
        }
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("exchangeDebitcard", new exchangeDebitcard());
      return processMap;
    }

    public static class exchangeDebitcard<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, exchangeDebitcard_args, ResponseData> {
      public exchangeDebitcard() {
        super("exchangeDebitcard");
      }

      public exchangeDebitcard_args getEmptyArgsInstance() {
        return new exchangeDebitcard_args();
      }

      public AsyncMethodCallback<ResponseData> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new AsyncMethodCallback<ResponseData>() { 
          public void onComplete(ResponseData o) {
            exchangeDebitcard_result result = new exchangeDebitcard_result();
            result.success = o;
            try {
              fcall.sendResponse(fb,result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
              return;
            } catch (Exception e) {
              LOGGER.error("Exception writing to internal frame buffer", e);
            }
            fb.close();
          }
          public void onError(Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TBase msg;
            exchangeDebitcard_result result = new exchangeDebitcard_result();
            if (e instanceof FailureException) {
                        result.qe = (FailureException) e;
                        result.setQeIsSet(true);
                        msg = result;
            }
             else 
            {
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TBase)new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
              return;
            } catch (Exception ex) {
              LOGGER.error("Exception writing to internal frame buffer", ex);
            }
            fb.close();
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, exchangeDebitcard_args args, org.apache.thrift.async.AsyncMethodCallback<ResponseData> resultHandler) throws TException {
        iface.exchangeDebitcard(args.paramMap,resultHandler);
      }
    }

  }

  public static class exchangeDebitcard_args implements org.apache.thrift.TBase<exchangeDebitcard_args, exchangeDebitcard_args._Fields>, java.io.Serializable, Cloneable, Comparable<exchangeDebitcard_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("exchangeDebitcard_args");

    private static final org.apache.thrift.protocol.TField PARAM_MAP_FIELD_DESC = new org.apache.thrift.protocol.TField("paramMap", org.apache.thrift.protocol.TType.MAP, (short)1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new exchangeDebitcard_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new exchangeDebitcard_argsTupleSchemeFactory());
    }

    public Map<String,String> paramMap; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      PARAM_MAP((short)1, "paramMap");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // PARAM_MAP
            return PARAM_MAP;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.PARAM_MAP, new org.apache.thrift.meta_data.FieldMetaData("paramMap", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
              new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
              new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(exchangeDebitcard_args.class, metaDataMap);
    }

    public exchangeDebitcard_args() {
    }

    public exchangeDebitcard_args(
      Map<String,String> paramMap)
    {
      this();
      this.paramMap = paramMap;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public exchangeDebitcard_args(exchangeDebitcard_args other) {
      if (other.isSetParamMap()) {
        Map<String,String> __this__paramMap = new HashMap<String,String>(other.paramMap);
        this.paramMap = __this__paramMap;
      }
    }

    public exchangeDebitcard_args deepCopy() {
      return new exchangeDebitcard_args(this);
    }

    @Override
    public void clear() {
      this.paramMap = null;
    }

    public int getParamMapSize() {
      return (this.paramMap == null) ? 0 : this.paramMap.size();
    }

    public void putToParamMap(String key, String val) {
      if (this.paramMap == null) {
        this.paramMap = new HashMap<String,String>();
      }
      this.paramMap.put(key, val);
    }

    public Map<String,String> getParamMap() {
      return this.paramMap;
    }

    public exchangeDebitcard_args setParamMap(Map<String,String> paramMap) {
      this.paramMap = paramMap;
      return this;
    }

    public void unsetParamMap() {
      this.paramMap = null;
    }

    /** Returns true if field paramMap is set (has been assigned a value) and false otherwise */
    public boolean isSetParamMap() {
      return this.paramMap != null;
    }

    public void setParamMapIsSet(boolean value) {
      if (!value) {
        this.paramMap = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case PARAM_MAP:
        if (value == null) {
          unsetParamMap();
        } else {
          setParamMap((Map<String,String>)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case PARAM_MAP:
        return getParamMap();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case PARAM_MAP:
        return isSetParamMap();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof exchangeDebitcard_args)
        return this.equals((exchangeDebitcard_args)that);
      return false;
    }

    public boolean equals(exchangeDebitcard_args that) {
      if (that == null)
        return false;

      boolean this_present_paramMap = true && this.isSetParamMap();
      boolean that_present_paramMap = true && that.isSetParamMap();
      if (this_present_paramMap || that_present_paramMap) {
        if (!(this_present_paramMap && that_present_paramMap))
          return false;
        if (!this.paramMap.equals(that.paramMap))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    @Override
    public int compareTo(exchangeDebitcard_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetParamMap()).compareTo(other.isSetParamMap());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetParamMap()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.paramMap, other.paramMap);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("exchangeDebitcard_args(");
      boolean first = true;

      sb.append("paramMap:");
      if (this.paramMap == null) {
        sb.append("null");
      } else {
        sb.append(this.paramMap);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class exchangeDebitcard_argsStandardSchemeFactory implements SchemeFactory {
      public exchangeDebitcard_argsStandardScheme getScheme() {
        return new exchangeDebitcard_argsStandardScheme();
      }
    }

    private static class exchangeDebitcard_argsStandardScheme extends StandardScheme<exchangeDebitcard_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, exchangeDebitcard_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // PARAM_MAP
              if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
                {
                  org.apache.thrift.protocol.TMap _map10 = iprot.readMapBegin();
                  struct.paramMap = new HashMap<String,String>(2*_map10.size);
                  for (int _i11 = 0; _i11 < _map10.size; ++_i11)
                  {
                    String _key12;
                    String _val13;
                    _key12 = iprot.readString();
                    _val13 = iprot.readString();
                    struct.paramMap.put(_key12, _val13);
                  }
                  iprot.readMapEnd();
                }
                struct.setParamMapIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, exchangeDebitcard_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.paramMap != null) {
          oprot.writeFieldBegin(PARAM_MAP_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.paramMap.size()));
            for (Map.Entry<String, String> _iter14 : struct.paramMap.entrySet())
            {
              oprot.writeString(_iter14.getKey());
              oprot.writeString(_iter14.getValue());
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class exchangeDebitcard_argsTupleSchemeFactory implements SchemeFactory {
      public exchangeDebitcard_argsTupleScheme getScheme() {
        return new exchangeDebitcard_argsTupleScheme();
      }
    }

    private static class exchangeDebitcard_argsTupleScheme extends TupleScheme<exchangeDebitcard_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, exchangeDebitcard_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetParamMap()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetParamMap()) {
          {
            oprot.writeI32(struct.paramMap.size());
            for (Map.Entry<String, String> _iter15 : struct.paramMap.entrySet())
            {
              oprot.writeString(_iter15.getKey());
              oprot.writeString(_iter15.getValue());
            }
          }
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, exchangeDebitcard_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          {
            org.apache.thrift.protocol.TMap _map16 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
            struct.paramMap = new HashMap<String,String>(2*_map16.size);
            for (int _i17 = 0; _i17 < _map16.size; ++_i17)
            {
              String _key18;
              String _val19;
              _key18 = iprot.readString();
              _val19 = iprot.readString();
              struct.paramMap.put(_key18, _val19);
            }
          }
          struct.setParamMapIsSet(true);
        }
      }
    }

  }

  public static class exchangeDebitcard_result implements org.apache.thrift.TBase<exchangeDebitcard_result, exchangeDebitcard_result._Fields>, java.io.Serializable, Cloneable, Comparable<exchangeDebitcard_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("exchangeDebitcard_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRUCT, (short)0);
    private static final org.apache.thrift.protocol.TField QE_FIELD_DESC = new org.apache.thrift.protocol.TField("qe", org.apache.thrift.protocol.TType.STRUCT, (short)1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new exchangeDebitcard_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new exchangeDebitcard_resultTupleSchemeFactory());
    }

    public ResponseData success; // required
    public FailureException qe; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success"),
      QE((short)1, "qe");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 0: // SUCCESS
            return SUCCESS;
          case 1: // QE
            return QE;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ResponseData.class)));
      tmpMap.put(_Fields.QE, new org.apache.thrift.meta_data.FieldMetaData("qe", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(exchangeDebitcard_result.class, metaDataMap);
    }

    public exchangeDebitcard_result() {
    }

    public exchangeDebitcard_result(
      ResponseData success,
      FailureException qe)
    {
      this();
      this.success = success;
      this.qe = qe;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public exchangeDebitcard_result(exchangeDebitcard_result other) {
      if (other.isSetSuccess()) {
        this.success = new ResponseData(other.success);
      }
      if (other.isSetQe()) {
        this.qe = new FailureException(other.qe);
      }
    }

    public exchangeDebitcard_result deepCopy() {
      return new exchangeDebitcard_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
      this.qe = null;
    }

    public ResponseData getSuccess() {
      return this.success;
    }

    public exchangeDebitcard_result setSuccess(ResponseData success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public FailureException getQe() {
      return this.qe;
    }

    public exchangeDebitcard_result setQe(FailureException qe) {
      this.qe = qe;
      return this;
    }

    public void unsetQe() {
      this.qe = null;
    }

    /** Returns true if field qe is set (has been assigned a value) and false otherwise */
    public boolean isSetQe() {
      return this.qe != null;
    }

    public void setQeIsSet(boolean value) {
      if (!value) {
        this.qe = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((ResponseData)value);
        }
        break;

      case QE:
        if (value == null) {
          unsetQe();
        } else {
          setQe((FailureException)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case QE:
        return getQe();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case QE:
        return isSetQe();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof exchangeDebitcard_result)
        return this.equals((exchangeDebitcard_result)that);
      return false;
    }

    public boolean equals(exchangeDebitcard_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_qe = true && this.isSetQe();
      boolean that_present_qe = true && that.isSetQe();
      if (this_present_qe || that_present_qe) {
        if (!(this_present_qe && that_present_qe))
          return false;
        if (!this.qe.equals(that.qe))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    @Override
    public int compareTo(exchangeDebitcard_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetQe()).compareTo(other.isSetQe());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetQe()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.qe, other.qe);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
      }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("exchangeDebitcard_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("qe:");
      if (this.qe == null) {
        sb.append("null");
      } else {
        sb.append(this.qe);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (success != null) {
        success.validate();
      }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class exchangeDebitcard_resultStandardSchemeFactory implements SchemeFactory {
      public exchangeDebitcard_resultStandardScheme getScheme() {
        return new exchangeDebitcard_resultStandardScheme();
      }
    }

    private static class exchangeDebitcard_resultStandardScheme extends StandardScheme<exchangeDebitcard_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, exchangeDebitcard_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.success = new ResponseData();
                struct.success.read(iprot);
                struct.setSuccessIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 1: // QE
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.qe = new FailureException();
                struct.qe.read(iprot);
                struct.setQeIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, exchangeDebitcard_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          struct.success.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.qe != null) {
          oprot.writeFieldBegin(QE_FIELD_DESC);
          struct.qe.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class exchangeDebitcard_resultTupleSchemeFactory implements SchemeFactory {
      public exchangeDebitcard_resultTupleScheme getScheme() {
        return new exchangeDebitcard_resultTupleScheme();
      }
    }

    private static class exchangeDebitcard_resultTupleScheme extends TupleScheme<exchangeDebitcard_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, exchangeDebitcard_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        if (struct.isSetQe()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetSuccess()) {
          struct.success.write(oprot);
        }
        if (struct.isSetQe()) {
          struct.qe.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, exchangeDebitcard_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          struct.success = new ResponseData();
          struct.success.read(iprot);
          struct.setSuccessIsSet(true);
        }
        if (incoming.get(1)) {
          struct.qe = new FailureException();
          struct.qe.read(iprot);
          struct.setQeIsSet(true);
        }
      }
    }

  }

}