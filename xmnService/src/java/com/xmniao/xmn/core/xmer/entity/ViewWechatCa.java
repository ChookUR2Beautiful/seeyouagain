package com.xmniao.xmn.core.xmer.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewWechatCa {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ViewWechatCa() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBuyUidIsNull() {
            addCriterion("buy_uid is null");
            return (Criteria) this;
        }

        public Criteria andBuyUidIsNotNull() {
            addCriterion("buy_uid is not null");
            return (Criteria) this;
        }

        public Criteria andBuyUidEqualTo(Integer value) {
            addCriterion("buy_uid =", value, "buyUid");
            return (Criteria) this;
        }

        public Criteria andBuyUidNotEqualTo(Integer value) {
            addCriterion("buy_uid <>", value, "buyUid");
            return (Criteria) this;
        }

        public Criteria andBuyUidGreaterThan(Integer value) {
            addCriterion("buy_uid >", value, "buyUid");
            return (Criteria) this;
        }

        public Criteria andBuyUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_uid >=", value, "buyUid");
            return (Criteria) this;
        }

        public Criteria andBuyUidLessThan(Integer value) {
            addCriterion("buy_uid <", value, "buyUid");
            return (Criteria) this;
        }

        public Criteria andBuyUidLessThanOrEqualTo(Integer value) {
            addCriterion("buy_uid <=", value, "buyUid");
            return (Criteria) this;
        }

        public Criteria andBuyUidIn(List<Integer> values) {
            addCriterion("buy_uid in", values, "buyUid");
            return (Criteria) this;
        }

        public Criteria andBuyUidNotIn(List<Integer> values) {
            addCriterion("buy_uid not in", values, "buyUid");
            return (Criteria) this;
        }

        public Criteria andBuyUidBetween(Integer value1, Integer value2) {
            addCriterion("buy_uid between", value1, value2, "buyUid");
            return (Criteria) this;
        }

        public Criteria andBuyUidNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_uid not between", value1, value2, "buyUid");
            return (Criteria) this;
        }

        public Criteria andSoldUidIsNull() {
            addCriterion("sold_uid is null");
            return (Criteria) this;
        }

        public Criteria andSoldUidIsNotNull() {
            addCriterion("sold_uid is not null");
            return (Criteria) this;
        }

        public Criteria andSoldUidEqualTo(Integer value) {
            addCriterion("sold_uid =", value, "soldUid");
            return (Criteria) this;
        }

        public Criteria andSoldUidNotEqualTo(Integer value) {
            addCriterion("sold_uid <>", value, "soldUid");
            return (Criteria) this;
        }

        public Criteria andSoldUidGreaterThan(Integer value) {
            addCriterion("sold_uid >", value, "soldUid");
            return (Criteria) this;
        }

        public Criteria andSoldUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sold_uid >=", value, "soldUid");
            return (Criteria) this;
        }

        public Criteria andSoldUidLessThan(Integer value) {
            addCriterion("sold_uid <", value, "soldUid");
            return (Criteria) this;
        }

        public Criteria andSoldUidLessThanOrEqualTo(Integer value) {
            addCriterion("sold_uid <=", value, "soldUid");
            return (Criteria) this;
        }

        public Criteria andSoldUidIn(List<Integer> values) {
            addCriterion("sold_uid in", values, "soldUid");
            return (Criteria) this;
        }

        public Criteria andSoldUidNotIn(List<Integer> values) {
            addCriterion("sold_uid not in", values, "soldUid");
            return (Criteria) this;
        }

        public Criteria andSoldUidBetween(Integer value1, Integer value2) {
            addCriterion("sold_uid between", value1, value2, "soldUid");
            return (Criteria) this;
        }

        public Criteria andSoldUidNotBetween(Integer value1, Integer value2) {
            addCriterion("sold_uid not between", value1, value2, "soldUid");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNull() {
            addCriterion("integral is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNotNull() {
            addCriterion("integral is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralEqualTo(BigDecimal value) {
            addCriterion("integral =", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotEqualTo(BigDecimal value) {
            addCriterion("integral <>", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThan(BigDecimal value) {
            addCriterion("integral >", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("integral >=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThan(BigDecimal value) {
            addCriterion("integral <", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThanOrEqualTo(BigDecimal value) {
            addCriterion("integral <=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralIn(List<BigDecimal> values) {
            addCriterion("integral in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotIn(List<BigDecimal> values) {
            addCriterion("integral not in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral not between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andViewDateIsNull() {
            addCriterion("view_date is null");
            return (Criteria) this;
        }

        public Criteria andViewDateIsNotNull() {
            addCriterion("view_date is not null");
            return (Criteria) this;
        }

        public Criteria andViewDateEqualTo(Date value) {
            addCriterion("view_date =", value, "viewDate");
            return (Criteria) this;
        }

        public Criteria andViewDateNotEqualTo(Date value) {
            addCriterion("view_date <>", value, "viewDate");
            return (Criteria) this;
        }

        public Criteria andViewDateGreaterThan(Date value) {
            addCriterion("view_date >", value, "viewDate");
            return (Criteria) this;
        }

        public Criteria andViewDateGreaterThanOrEqualTo(Date value) {
            addCriterion("view_date >=", value, "viewDate");
            return (Criteria) this;
        }

        public Criteria andViewDateLessThan(Date value) {
            addCriterion("view_date <", value, "viewDate");
            return (Criteria) this;
        }

        public Criteria andViewDateLessThanOrEqualTo(Date value) {
            addCriterion("view_date <=", value, "viewDate");
            return (Criteria) this;
        }

        public Criteria andViewDateIn(List<Date> values) {
            addCriterion("view_date in", values, "viewDate");
            return (Criteria) this;
        }

        public Criteria andViewDateNotIn(List<Date> values) {
            addCriterion("view_date not in", values, "viewDate");
            return (Criteria) this;
        }

        public Criteria andViewDateBetween(Date value1, Date value2) {
            addCriterion("view_date between", value1, value2, "viewDate");
            return (Criteria) this;
        }

        public Criteria andViewDateNotBetween(Date value1, Date value2) {
            addCriterion("view_date not between", value1, value2, "viewDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}