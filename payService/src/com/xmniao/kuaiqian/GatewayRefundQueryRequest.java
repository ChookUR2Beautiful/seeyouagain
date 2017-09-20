/**
 * GatewayRefundQueryRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.xmniao.kuaiqian;

public class GatewayRefundQueryRequest  implements java.io.Serializable {
    private java.lang.String ROrderId;

    private java.lang.String customerBatchId;

    private java.lang.String endDate;

    private java.lang.String extra_output_column;

    private java.lang.String lastupdateEndDate;

    private java.lang.String lastupdateStartDate;

    private java.lang.String merchantAcctId;

    private java.lang.String orderId;

    private java.lang.String requestPage;

    private java.lang.String seqId;

    private java.lang.String signMsg;

    private java.lang.String signType;

    private java.lang.String startDate;

    private java.lang.String status;

    private java.lang.String version;

    public GatewayRefundQueryRequest() {
    }

    public GatewayRefundQueryRequest(
           java.lang.String ROrderId,
           java.lang.String customerBatchId,
           java.lang.String endDate,
           java.lang.String extra_output_column,
           java.lang.String lastupdateEndDate,
           java.lang.String lastupdateStartDate,
           java.lang.String merchantAcctId,
           java.lang.String orderId,
           java.lang.String requestPage,
           java.lang.String seqId,
           java.lang.String signMsg,
           java.lang.String signType,
           java.lang.String startDate,
           java.lang.String status,
           java.lang.String version) {
           this.ROrderId = ROrderId;
           this.customerBatchId = customerBatchId;
           this.endDate = endDate;
           this.extra_output_column = extra_output_column;
           this.lastupdateEndDate = lastupdateEndDate;
           this.lastupdateStartDate = lastupdateStartDate;
           this.merchantAcctId = merchantAcctId;
           this.orderId = orderId;
           this.requestPage = requestPage;
           this.seqId = seqId;
           this.signMsg = signMsg;
           this.signType = signType;
           this.startDate = startDate;
           this.status = status;
           this.version = version;
    }


    /**
     * Gets the ROrderId value for this GatewayRefundQueryRequest.
     * 
     * @return ROrderId
     */
    public java.lang.String getROrderId() {
        return ROrderId;
    }


    /**
     * Sets the ROrderId value for this GatewayRefundQueryRequest.
     * 
     * @param ROrderId
     */
    public void setROrderId(java.lang.String ROrderId) {
        this.ROrderId = ROrderId;
    }


    /**
     * Gets the customerBatchId value for this GatewayRefundQueryRequest.
     * 
     * @return customerBatchId
     */
    public java.lang.String getCustomerBatchId() {
        return customerBatchId;
    }


    /**
     * Sets the customerBatchId value for this GatewayRefundQueryRequest.
     * 
     * @param customerBatchId
     */
    public void setCustomerBatchId(java.lang.String customerBatchId) {
        this.customerBatchId = customerBatchId;
    }


    /**
     * Gets the endDate value for this GatewayRefundQueryRequest.
     * 
     * @return endDate
     */
    public java.lang.String getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this GatewayRefundQueryRequest.
     * 
     * @param endDate
     */
    public void setEndDate(java.lang.String endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the extra_output_column value for this GatewayRefundQueryRequest.
     * 
     * @return extra_output_column
     */
    public java.lang.String getExtra_output_column() {
        return extra_output_column;
    }


    /**
     * Sets the extra_output_column value for this GatewayRefundQueryRequest.
     * 
     * @param extra_output_column
     */
    public void setExtra_output_column(java.lang.String extra_output_column) {
        this.extra_output_column = extra_output_column;
    }


    /**
     * Gets the lastupdateEndDate value for this GatewayRefundQueryRequest.
     * 
     * @return lastupdateEndDate
     */
    public java.lang.String getLastupdateEndDate() {
        return lastupdateEndDate;
    }


    /**
     * Sets the lastupdateEndDate value for this GatewayRefundQueryRequest.
     * 
     * @param lastupdateEndDate
     */
    public void setLastupdateEndDate(java.lang.String lastupdateEndDate) {
        this.lastupdateEndDate = lastupdateEndDate;
    }


    /**
     * Gets the lastupdateStartDate value for this GatewayRefundQueryRequest.
     * 
     * @return lastupdateStartDate
     */
    public java.lang.String getLastupdateStartDate() {
        return lastupdateStartDate;
    }


    /**
     * Sets the lastupdateStartDate value for this GatewayRefundQueryRequest.
     * 
     * @param lastupdateStartDate
     */
    public void setLastupdateStartDate(java.lang.String lastupdateStartDate) {
        this.lastupdateStartDate = lastupdateStartDate;
    }


    /**
     * Gets the merchantAcctId value for this GatewayRefundQueryRequest.
     * 
     * @return merchantAcctId
     */
    public java.lang.String getMerchantAcctId() {
        return merchantAcctId;
    }


    /**
     * Sets the merchantAcctId value for this GatewayRefundQueryRequest.
     * 
     * @param merchantAcctId
     */
    public void setMerchantAcctId(java.lang.String merchantAcctId) {
        this.merchantAcctId = merchantAcctId;
    }


    /**
     * Gets the orderId value for this GatewayRefundQueryRequest.
     * 
     * @return orderId
     */
    public java.lang.String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this GatewayRefundQueryRequest.
     * 
     * @param orderId
     */
    public void setOrderId(java.lang.String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the requestPage value for this GatewayRefundQueryRequest.
     * 
     * @return requestPage
     */
    public java.lang.String getRequestPage() {
        return requestPage;
    }


    /**
     * Sets the requestPage value for this GatewayRefundQueryRequest.
     * 
     * @param requestPage
     */
    public void setRequestPage(java.lang.String requestPage) {
        this.requestPage = requestPage;
    }


    /**
     * Gets the seqId value for this GatewayRefundQueryRequest.
     * 
     * @return seqId
     */
    public java.lang.String getSeqId() {
        return seqId;
    }


    /**
     * Sets the seqId value for this GatewayRefundQueryRequest.
     * 
     * @param seqId
     */
    public void setSeqId(java.lang.String seqId) {
        this.seqId = seqId;
    }


    /**
     * Gets the signMsg value for this GatewayRefundQueryRequest.
     * 
     * @return signMsg
     */
    public java.lang.String getSignMsg() {
        return signMsg;
    }


    /**
     * Sets the signMsg value for this GatewayRefundQueryRequest.
     * 
     * @param signMsg
     */
    public void setSignMsg(java.lang.String signMsg) {
        this.signMsg = signMsg;
    }


    /**
     * Gets the signType value for this GatewayRefundQueryRequest.
     * 
     * @return signType
     */
    public java.lang.String getSignType() {
        return signType;
    }


    /**
     * Sets the signType value for this GatewayRefundQueryRequest.
     * 
     * @param signType
     */
    public void setSignType(java.lang.String signType) {
        this.signType = signType;
    }


    /**
     * Gets the startDate value for this GatewayRefundQueryRequest.
     * 
     * @return startDate
     */
    public java.lang.String getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this GatewayRefundQueryRequest.
     * 
     * @param startDate
     */
    public void setStartDate(java.lang.String startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the status value for this GatewayRefundQueryRequest.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this GatewayRefundQueryRequest.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the version value for this GatewayRefundQueryRequest.
     * 
     * @return version
     */
    public java.lang.String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this GatewayRefundQueryRequest.
     * 
     * @param version
     */
    public void setVersion(java.lang.String version) {
        this.version = version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GatewayRefundQueryRequest)) return false;
        GatewayRefundQueryRequest other = (GatewayRefundQueryRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ROrderId==null && other.getROrderId()==null) || 
             (this.ROrderId!=null &&
              this.ROrderId.equals(other.getROrderId()))) &&
            ((this.customerBatchId==null && other.getCustomerBatchId()==null) || 
             (this.customerBatchId!=null &&
              this.customerBatchId.equals(other.getCustomerBatchId()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.extra_output_column==null && other.getExtra_output_column()==null) || 
             (this.extra_output_column!=null &&
              this.extra_output_column.equals(other.getExtra_output_column()))) &&
            ((this.lastupdateEndDate==null && other.getLastupdateEndDate()==null) || 
             (this.lastupdateEndDate!=null &&
              this.lastupdateEndDate.equals(other.getLastupdateEndDate()))) &&
            ((this.lastupdateStartDate==null && other.getLastupdateStartDate()==null) || 
             (this.lastupdateStartDate!=null &&
              this.lastupdateStartDate.equals(other.getLastupdateStartDate()))) &&
            ((this.merchantAcctId==null && other.getMerchantAcctId()==null) || 
             (this.merchantAcctId!=null &&
              this.merchantAcctId.equals(other.getMerchantAcctId()))) &&
            ((this.orderId==null && other.getOrderId()==null) || 
             (this.orderId!=null &&
              this.orderId.equals(other.getOrderId()))) &&
            ((this.requestPage==null && other.getRequestPage()==null) || 
             (this.requestPage!=null &&
              this.requestPage.equals(other.getRequestPage()))) &&
            ((this.seqId==null && other.getSeqId()==null) || 
             (this.seqId!=null &&
              this.seqId.equals(other.getSeqId()))) &&
            ((this.signMsg==null && other.getSignMsg()==null) || 
             (this.signMsg!=null &&
              this.signMsg.equals(other.getSignMsg()))) &&
            ((this.signType==null && other.getSignType()==null) || 
             (this.signType!=null &&
              this.signType.equals(other.getSignType()))) &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getROrderId() != null) {
            _hashCode += getROrderId().hashCode();
        }
        if (getCustomerBatchId() != null) {
            _hashCode += getCustomerBatchId().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getExtra_output_column() != null) {
            _hashCode += getExtra_output_column().hashCode();
        }
        if (getLastupdateEndDate() != null) {
            _hashCode += getLastupdateEndDate().hashCode();
        }
        if (getLastupdateStartDate() != null) {
            _hashCode += getLastupdateStartDate().hashCode();
        }
        if (getMerchantAcctId() != null) {
            _hashCode += getMerchantAcctId().hashCode();
        }
        if (getOrderId() != null) {
            _hashCode += getOrderId().hashCode();
        }
        if (getRequestPage() != null) {
            _hashCode += getRequestPage().hashCode();
        }
        if (getSeqId() != null) {
            _hashCode += getSeqId().hashCode();
        }
        if (getSignMsg() != null) {
            _hashCode += getSignMsg().hashCode();
        }
        if (getSignType() != null) {
            _hashCode += getSignType().hashCode();
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GatewayRefundQueryRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gatewayrefundquery.dto.domain.seashell.bill99.com", "GatewayRefundQueryRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ROrderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ROrderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerBatchId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customerBatchId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extra_output_column");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extra_output_column"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastupdateEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastupdateEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastupdateStartDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastupdateStartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merchantAcctId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "merchantAcctId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestPage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestPage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seqId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seqId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
