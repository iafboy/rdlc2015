package com.expocms.server.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RdFeedbackExt {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public RdFeedbackExt() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIsNull() {
            addCriterion("submit_date is null");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIsNotNull() {
            addCriterion("submit_date is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitDateEqualTo(Date value) {
            addCriterionForJDBCDate("submit_date =", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("submit_date <>", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateGreaterThan(Date value) {
            addCriterionForJDBCDate("submit_date >", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("submit_date >=", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateLessThan(Date value) {
            addCriterionForJDBCDate("submit_date <", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("submit_date <=", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIn(List<Date> values) {
            addCriterionForJDBCDate("submit_date in", values, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("submit_date not in", values, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("submit_date between", value1, value2, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("submit_date not between", value1, value2, "submitDate");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoIsNull() {
            addCriterion("additional_info is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoIsNotNull() {
            addCriterion("additional_info is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoEqualTo(String value) {
            addCriterion("additional_info =", value, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoNotEqualTo(String value) {
            addCriterion("additional_info <>", value, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoGreaterThan(String value) {
            addCriterion("additional_info >", value, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoGreaterThanOrEqualTo(String value) {
            addCriterion("additional_info >=", value, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoLessThan(String value) {
            addCriterion("additional_info <", value, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoLessThanOrEqualTo(String value) {
            addCriterion("additional_info <=", value, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoLike(String value) {
            addCriterion("additional_info like", value, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoNotLike(String value) {
            addCriterion("additional_info not like", value, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoIn(List<String> values) {
            addCriterion("additional_info in", values, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoNotIn(List<String> values) {
            addCriterion("additional_info not in", values, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoBetween(String value1, String value2) {
            addCriterion("additional_info between", value1, value2, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andAdditionalInfoNotBetween(String value1, String value2) {
            addCriterion("additional_info not between", value1, value2, "additionalInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdIsNull() {
            addCriterion("custom_service_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdIsNotNull() {
            addCriterion("custom_service_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdEqualTo(Long value) {
            addCriterion("custom_service_id =", value, "customServiceId");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdNotEqualTo(Long value) {
            addCriterion("custom_service_id <>", value, "customServiceId");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdGreaterThan(Long value) {
            addCriterion("custom_service_id >", value, "customServiceId");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("custom_service_id >=", value, "customServiceId");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdLessThan(Long value) {
            addCriterion("custom_service_id <", value, "customServiceId");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdLessThanOrEqualTo(Long value) {
            addCriterion("custom_service_id <=", value, "customServiceId");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdIn(List<Long> values) {
            addCriterion("custom_service_id in", values, "customServiceId");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdNotIn(List<Long> values) {
            addCriterion("custom_service_id not in", values, "customServiceId");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdBetween(Long value1, Long value2) {
            addCriterion("custom_service_id between", value1, value2, "customServiceId");
            return (Criteria) this;
        }

        public Criteria andCustomServiceIdNotBetween(Long value1, Long value2) {
            addCriterion("custom_service_id not between", value1, value2, "customServiceId");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoIsNull() {
            addCriterion("custom_service_info is null");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoIsNotNull() {
            addCriterion("custom_service_info is not null");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoEqualTo(String value) {
            addCriterion("custom_service_info =", value, "customServiceInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoNotEqualTo(String value) {
            addCriterion("custom_service_info <>", value, "customServiceInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoGreaterThan(String value) {
            addCriterion("custom_service_info >", value, "customServiceInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoGreaterThanOrEqualTo(String value) {
            addCriterion("custom_service_info >=", value, "customServiceInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoLessThan(String value) {
            addCriterion("custom_service_info <", value, "customServiceInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoLessThanOrEqualTo(String value) {
            addCriterion("custom_service_info <=", value, "customServiceInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoLike(String value) {
            addCriterion("custom_service_info like", value, "customServiceInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoNotLike(String value) {
            addCriterion("custom_service_info not like", value, "customServiceInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoIn(List<String> values) {
            addCriterion("custom_service_info in", values, "customServiceInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoNotIn(List<String> values) {
            addCriterion("custom_service_info not in", values, "customServiceInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoBetween(String value1, String value2) {
            addCriterion("custom_service_info between", value1, value2, "customServiceInfo");
            return (Criteria) this;
        }

        public Criteria andCustomServiceInfoNotBetween(String value1, String value2) {
            addCriterion("custom_service_info not between", value1, value2, "customServiceInfo");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table rd_feedback
     *
     * @mbggenerated do_not_delete_during_merge Sun Aug 16 01:14:17 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table rd_feedback
     *
     * @mbggenerated Sun Aug 16 01:14:17 CST 2015
     */
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