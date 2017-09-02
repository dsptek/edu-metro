package nara.share.springdata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import javax.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class QueryUtil {
    //
    //
    public static <T, U> Specification<T> in(final String key, final List<Object> values, Class<U> paramType) {
        //
        return new Specification<T>() {
            //
            @SuppressWarnings("unchecked")
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return root.get(key).in((List<U>) values);
            }
        };
    }
    public static <T, U> Specification<T> in(final String key, final List<Object> values, Class<U> paramType, String... embeddedDocs) {
        //
        return new Specification<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                Path<String> path = root.get(embeddedDocs[0]);
                for (String embeddedDoc : Arrays.asList(embeddedDocs).subList(1, embeddedDocs.length)) {
                    path = path.get(embeddedDoc);
                }
                return path.<String>get(key).in((List<U>) values);
            }
        };
    }
    public static <T, U> Specification<T> equal(final String key, final Object value, Class<U> paramType) {
        //
        return new Specification<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.equal(root.<U>get(key), (U) value);
            }
        };
    }
    public static <T, U> Specification<T> notEqual(final String key, final Object value, Class<U> paramType) {
        //
        return new Specification<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.notEqual(root.<U>get(key), (U) value);
            }
        };
    }
    public static <T, U> Specification<T> equal(final String key, final Object value, Class<U> paramType, String... embeddedDocs) {
        //
        return new Specification<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                Path<String> path = root.get(embeddedDocs[0]);
                for (String embeddedDoc : Arrays.asList(embeddedDocs).subList(1, embeddedDocs.length)) {
                    path = path.get(embeddedDoc);
                }
                return cb.equal(path.<U>get(key), (U) value);
            }
        };
    }
    public static <T, U> Specification<T> notEqual(final String key, final Object value, Class<U> paramType, String... embeddedDocs) {
        //
        return new Specification<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                Path<String> path = root.get(embeddedDocs[0]);
                for (String embeddedDoc : Arrays.asList(embeddedDocs).subList(1, embeddedDocs.length)) {
                    path = path.get(embeddedDoc);
                }
                return cb.notEqual(path.<U>get(key), (U) value);
            }
        };
    }
    public static <T> Specification<T> like(String key, String value) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.like(cb.upper(root.<String>get(key)), "%"+value.toUpperCase()+"%");
            }
        };
    }
    public static <T> Specification<T> like(String key, String value, String... embeddedDocs) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                Path<String> path = root.get(embeddedDocs[0]);
                for (String embeddedDoc : Arrays.asList(embeddedDocs).subList(1, embeddedDocs.length)) {
                    path = path.get(embeddedDoc);
                }
                return cb.like(cb.upper(path.<String>get(key)), "%"+value.toUpperCase()+"%");
            }
        };
    }
    public static <T> Specification<T> between(final String key, final Integer min, final Integer max) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.between(root.<Integer>get(key), min, max);
            }
        };
    }
    public static <T> Specification<T> between(final String key, final Double min, final Double max) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.between(root.<Double>get(key), min, max);
            }
        };
    }
    public static <T> Specification<T> between(final String key, final LocalDate min, final LocalDate max) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.between(root.<LocalDate>get(key), min, max);
            }
        };
    }
    public static <T> Specification<T> between(final String key, final LocalDateTime min, final LocalDateTime max) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.between(root.<LocalDateTime>get(key), min, max);
            }
        };
    }
    public static <T> Specification<T> lessThanOrEqualTo(final String key, final Integer value) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.lessThanOrEqualTo(root.<Integer>get(key), (Integer)value);
            }
        };
    }
    public static <T> Specification<T> lessThanOrEqualTo(final String key, final Double value) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.lessThanOrEqualTo(root.<Double>get(key), (Double)value);
            }
        };
    }
    public static <T> Specification<T> greaterThanOrEqualTo(final String key, final Integer value) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.greaterThanOrEqualTo(root.<Integer>get(key), (Integer)value);
            }
        };
    }
    public static <T> Specification<T> greaterThanOrEqualTo(final String key, final Double value) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.greaterThanOrEqualTo(root.<Double>get(key), (Double)value);
            }
        };
    }
    public static <T> Specification<T> lessThanOrEqualTo(final String key, final LocalDate value) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.lessThanOrEqualTo(root.<LocalDate>get(key), (LocalDate)value);
            }
        };
    }
    public static <T> Specification<T> lessThanOrEqualTo(final String key, final LocalDate value,String... embeddedDocs) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                Path<String> path = root.get(embeddedDocs[0]);
                for (String embeddedDoc : Arrays.asList(embeddedDocs).subList(1, embeddedDocs.length)) {
                    path = path.get(embeddedDoc);
                }
                return cb.lessThanOrEqualTo(path.<LocalDate>get(key), (LocalDate)value);
            }
        };
    }
    public static <T> Specification<T> greaterThanOrEqualTo(final String key, final LocalDate value) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.greaterThanOrEqualTo(root.<LocalDate>get(key), (LocalDate)value);
            }
        };
    }
    public static <T> Specification<T> greaterThanOrEqualTo(final String key, final LocalDate value,String... embeddedDocs) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                Path<String> path = root.get(embeddedDocs[0]);
                for (String embeddedDoc : Arrays.asList(embeddedDocs).subList(1, embeddedDocs.length)) {
                    path = path.get(embeddedDoc);
                }
                return cb.greaterThanOrEqualTo(path.<LocalDate>get(key), (LocalDate)value);
            }
        };
    }
    public static <T> Specification<T> lessThanOrEqualTo(final String key, final LocalDateTime value) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.lessThanOrEqualTo(root.<LocalDateTime>get(key), (LocalDateTime)value);
            }
        };
    }
    public static <T> Specification<T> greaterThanOrEqualTo(final String key, final LocalDateTime value) {
        //
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //
                return cb.greaterThanOrEqualTo(root.<LocalDateTime>get(key), (LocalDateTime)value);
            }
        };
    }
    @SuppressWarnings({"rawtypes" })
    public static boolean isNullOrEmpty(Object param) {
        //
        if (param == null || param.equals("null") || param.toString().isEmpty()) {
            return true;
        }
        if (param instanceof List) {
            if(( (List) param ).isEmpty() ) {
                return true;
            }
        }
        return false;
    }
}