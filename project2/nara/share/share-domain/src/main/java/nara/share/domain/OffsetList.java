/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.
 */

package nara.share.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * OffsetList
 * <pre>
 *   Service 및 Store I/F에서 페이징처리가 필요한 다건조회 시 사용합니다.
 *   오퍼레이션 매개변수를 다음과 같이 설정하고, 반환형은 OffsetList를 사용합니다.
 *
 *   예시 #1. 특정위치에서 최대갯수만큼 조회하기
 *   List<AisUser> findUsers(int offset, int limit);
 *
 *   예시 #2. 조회조건이 있는 경우, offset와 limit 매개변수는 항상 맨뒤에 위치시킵니다.
 *   List<AisUser> findUsersByName(String name, int offset, int limit);
 * </pre>
 *
 * @param <T>
 * @author <a href="mailto:hyunohkim@nextree.co.kr">Kim, Hyunoh</a>
 * @since 2015. 7. 29.
 */
public class OffsetList<T> implements Iterable<T> {
    //
    private List<T> results;
    private int totalCount;

    protected OffsetList() {
        //
        this.results = new ArrayList<>();
    }

    public OffsetList(int totalCount) {
        //
        this();
        this.totalCount = totalCount;
    }

    public OffsetList(List<T> results, int totalCount) {
        //
        this.results = results;
        this.totalCount = totalCount;
    }

    @Override
    public Iterator<T> iterator() {
        //
        return results.iterator();
    }

    public int size() {
        //
        return (results != null) ? results.size() : 0;
    }

    public T get(int index) {
        //
        return (results != null) ? results.get(index) : null;
    }

    public void add(T result) {
        //
        results.add(result);
    }

    public boolean isEmpty() {
        //
        return (results == null || results.isEmpty());
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}