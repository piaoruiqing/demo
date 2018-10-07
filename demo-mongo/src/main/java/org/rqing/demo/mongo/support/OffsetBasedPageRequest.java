/*
 * File Name:OffsetBasedPageRequest.java is created on 2018年10月7日下午3:12:59 by piaoruiqing
 *
 * Copyright (c) 2018, xiaoyujiaoiyu technology All Rights Reserved.
 * 
 */
package org.rqing.demo.mongo.support;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/07 15:12
 *
 * @since JDK 1.8
 */
public class OffsetBasedPageRequest implements Pageable, Serializable {
    
    private static final long serialVersionUID = -6968995405824754937L;
    
    private int limit;
    private long offset;
    private final Sort sort;
    
    /**
     * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     * @param sort can be {@literal null}.
     */
    public OffsetBasedPageRequest(long offset, int limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }
    
    /**
     * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     * @param direction the direction of the {@link Sort} to be specified, can be {@literal null}.
     * @param properties the properties to sort by, must not be {@literal null} or empty.
     */
    public OffsetBasedPageRequest(long offset, int limit, Sort.Direction direction, String... properties) {
        this(offset, limit, new Sort(direction, properties));
    }
    
    /**
     * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit the size of the elements to be returned.
     */
    public OffsetBasedPageRequest(long offset, int limit) {
        this(offset, limit, new Sort(Sort.Direction.ASC,"id"));
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageNumber()
     */
    @Override
    public int getPageNumber() {
        return (int)(offset / limit);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageSize()
     */
    @Override
    public int getPageSize() {
        return limit;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getOffset()
     */
    @Override
    public long getOffset() {
        return offset;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getSort()
     */
    @Override
    public Sort getSort() {
        return sort;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#next()
     */
    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(getOffset() + getPageSize(), getPageSize(), getSort());
    }
    
    public OffsetBasedPageRequest previous() {
        return hasPrevious() ? new OffsetBasedPageRequest(getOffset() - getPageSize(), getPageSize(), getSort()) : this;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#previousOrFirst()
     */
    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#first()
     */
    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(0, getPageSize(), getSort());
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#hasPrevious()
     */
    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (!(object instanceof OffsetBasedPageRequest)) return false;

        OffsetBasedPageRequest that = (OffsetBasedPageRequest) object;

        return new EqualsBuilder()
                .append(limit, that.limit)
                .append(offset, that.offset)
                .append(sort, that.sort)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(limit)
                .append(offset)
                .append(sort)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("limit", limit)
                .append("offset", offset)
                .append("sort", sort)
                .toString();
    }

}
