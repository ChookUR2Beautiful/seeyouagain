package com.xmniao.domain.urs;

import java.util.Date;

public class UrsEarningsRelation {
    private Integer id;

    private Integer uid;

    private Integer objectOriented;
	
    private String uidRelationChain;

    private String uidRelationChainNname;
	
    private Integer uidRelationChainLevel;

    private Integer indirectUid;

    private Integer referrerSellerid;

    private Integer referrerSellertype;

    private Date createTime;

    private Integer manorLevel;
    
    private Integer manorNectar;
    private Long parentUid;

    public Long getParentUid() {
        return parentUid;
    }

    public void setParentUid(Long parentUid) {
        this.parentUid = parentUid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getObjectOriented() {
        return objectOriented;
    }

    public void setObjectOriented(Integer objectOriented) {
        this.objectOriented = objectOriented;
    }

    public Integer getUidRelationChainLevel() {
        return uidRelationChainLevel;
    }

    public void setUidRelationChainLevel(Integer uidRelationChainLevel) {
        this.uidRelationChainLevel = uidRelationChainLevel;
    }

    public Integer getIndirectUid() {
        return indirectUid;
    }

    public void setIndirectUid(Integer indirectUid) {
        this.indirectUid = indirectUid;
    }

    public Integer getReferrerSellerid() {
        return referrerSellerid;
    }

    public void setReferrerSellerid(Integer referrerSellerid) {
        this.referrerSellerid = referrerSellerid;
    }

    public Integer getReferrerSellertype() {
        return referrerSellertype;
    }

    public void setReferrerSellertype(Integer referrerSellertype) {
        this.referrerSellertype = referrerSellertype;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getUidRelationChain() {
		return uidRelationChain;
	}

	public void setUidRelationChain(String uidRelationChain) {
		this.uidRelationChain = uidRelationChain;
	}

	public String getUidRelationChainNname() {
		return uidRelationChainNname;
	}

	public void setUidRelationChainNname(String uidRelationChainNname) {
		this.uidRelationChainNname = uidRelationChainNname;
	}

	public Integer getManorLevel() {
		return manorLevel;
	}

	public void setManorLevel(Integer manorLevel) {
		this.manorLevel = manorLevel;
	}

	public Integer getManorNectar() {
		return manorNectar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrsEarningsRelation other = (UrsEarningsRelation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setManorNectar(Integer manorNectar) {
		this.manorNectar = manorNectar;
	}

	public UrsEarningsRelation() {
		super();
	}

	public UrsEarningsRelation(Integer uid, Integer objectOriented) {
		super();
		this.uid = uid;
		this.objectOriented = objectOriented;
	}
    
	
}