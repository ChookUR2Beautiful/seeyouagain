package com.xmn.saas.entity.bill;

public class LivePayOrderWithBLOBs extends LivePayOrder {
    private String receipt;

    private String uidRelationChain;

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt == null ? null : receipt.trim();
    }

    public String getUidRelationChain() {
        return uidRelationChain;
    }

    public void setUidRelationChain(String uidRelationChain) {
        this.uidRelationChain = uidRelationChain == null ? null : uidRelationChain.trim();
    }
}