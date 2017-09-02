package nara.share.springdata.page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class OffsetRequest implements Pageable, Serializable {

    private final int offset;
    private final int size;
    private final Sort sort;

    public OffsetRequest(int offset, int size) {
        this(offset, size, null);
    }

    public OffsetRequest(int page, int size, Sort.Direction direction, String... properties) {
        this(page, size, new Sort(direction, properties));
    }

    public OffsetRequest(int offset, int size, Sort sort) {
        this.offset = offset;
        this.size = size;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return offset / size;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetRequest(getPageNumber() + 1, getPageSize(), getSort());
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    public Pageable previous() {
        return getPageNumber() == 0 ? this : new OffsetRequest(getPageNumber() - 1, getPageSize(), getSort());
    }

    @Override
    public Pageable first() {
        return new OffsetRequest(0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return getPageNumber() > 0;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OffsetRequest)) {
            return false;
        }

        OffsetRequest that = (OffsetRequest) obj;

        boolean sortEqual = this.sort == null ? that.sort == null : this.sort.equals(that.sort);

        return super.equals(that) && sortEqual;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + (null == sort ? 0 : sort.hashCode());
    }

    @Override
    public String toString() {
        return String.format("Page request [number: %d, size %d, sort: %s]", getPageNumber(), getPageSize(),
                sort == null ? null : sort.toString());
    }
}
