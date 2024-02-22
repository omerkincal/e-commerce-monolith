package com.example.ecommercewebapp.library.datasourceloadoptions;

import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.SneakyThrows;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataSourceLoadOptionsUtil {

    private DataSourceLoadOptionsUtil(){}

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    @SneakyThrows
    public static <T> Specification<T> toSpec(DataSourceLoadOptions dataSourceLoadOptions, Class<T> clazz) {
        var filters = dataSourceLoadOptions.getFilter();
        Specification<T> spec = null;
        ArrayList<Field> sourceFields = new ArrayList<>(Arrays.stream(clazz.getDeclaredFields()).toList());
        sourceFields.addAll(Arrays.stream(clazz.getSuperclass().getDeclaredFields()).toList());
        if (filters != null && !filters.isEmpty()) {
            for (var i = 0; i < filters.size(); i++) {
                var filter = filters.get(i);
                if (filter.size() >= 3) {
                    Specification<T> filterSpec;
                    Field field = sourceFields.stream().filter(sourceField -> sourceField.getName().equals(filter.get(0)))
                            .findFirst().orElseThrow(() -> new CoreException(MessageCodes.FIELD_NOT_FOUND,filter.get(0)));

                    switch (filter.get(1)) {
                        case "contains" -> filterSpec = (root, query, criteriaBuilder) ->
                                criteriaBuilder.like(root.get(filter.get(0)), "%" + filter.get(2) + "%");
                        case "equals" -> filterSpec = equals(field.getType(), filter.get(0), filter.get(2));
                        case "between" -> filterSpec = between(field.getType(), filter.get(0), filter.get(2), filter.get(3));
                        case "greaterThan" -> filterSpec = greaterThan(field.getType(), filter.get(0), filter.get(2));
                        case "lessThan" -> filterSpec = lessThan(field.getType(), filter.get(0), filter.get(2));
                        case "isMember" -> {
                            List<String> stringList = List.of(filter.get(2).split(","));
                            filterSpec = isInList(filter.get(0), stringList);
                        }
                        case "has" -> {
                            Set<String> stringList = Set.of(filter.get(2).split(","));
                            filterSpec = has(filter.get(0), stringList);
                        }
                        default -> filterSpec = null;
                    }

                    if (filterSpec != null) {
                        spec = (spec == null) ? Specification.where(filterSpec) : spec.and(filterSpec);
                    }
                }
            }
        }

        return spec != null ? spec : Specification.where(null);
    }

    private static <T> Specification<T> equals(Class<?> type,String name, String value) {
        if (type == Date.class) {
            return (root, query, criteriaBuilder) ->
            {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    return criteriaBuilder.equal(root.get(name), dateFormat.parse(value));
                } catch (ParseException e) {
                    throw new IllegalArgumentException();
                }
            };
        }
        if ( type == Boolean.class || type == boolean.class) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(name), Boolean.parseBoolean(value));
        }
        if (type.isEnum()) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(name), Enum.valueOf((Class<Enum>) type, value));
        }
       return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(name), value);
    }

    public static <T> Specification<T> isInList(String fieldName, List<?> values) {
        return (root, query, criteriaBuilder) -> {
            if (values == null || values.isEmpty()) {
                return null;
            }

            Predicate[] predicates = values.stream()
                    .map(value -> criteriaBuilder.equal(root.get(fieldName), value))
                    .toArray(Predicate[]::new);

            return criteriaBuilder.or(predicates);
        };
    }

    public static <T> Specification<T> has(String attribute,Set<String> ids) {
        return (root, query, criteriaBuilder) -> {
            Join<T, String> categoriesJoin = root.join(attribute, JoinType.INNER);
            return criteriaBuilder.isTrue(categoriesJoin.in(ids));
        };
    }

    private static <T> Specification<T> greaterThan(Class<?> type,String name, String value) {
        if (type == Date.class) {
            return (root, query, criteriaBuilder) ->
            {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    return criteriaBuilder.greaterThan(root.get(name), dateFormat.parse(value));
                } catch (ParseException e) {
                    throw new IllegalArgumentException();
                }
            };
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get(name), value);
    }

    private static <T> Specification<T> lessThan(Class<?> type,String name, String value) {
        if (type == Date.class) {
            return (root, query, criteriaBuilder) ->
            {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    return criteriaBuilder.lessThan(root.get(name), dateFormat.parse(value));
                } catch (ParseException e) {
                    throw new IllegalArgumentException();
                }
            };
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get(name), value);
    }

    private static <T> Specification<T> between(Class<?> type,String name, String value, String value2){
        if(type == String.class){
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get(name), value, value2);
        } else if (type == long.class || type == Long.class) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get(name), Long.parseLong(value), Long.parseLong(value2));
        }
        else if (type == int.class || type == Integer.class) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get(name), Integer.parseInt(value), Integer.parseInt(value2));
        }
        else if (type == short.class || type == Short.class) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get(name), Short.parseShort(value), Short.parseShort(value2));
        }
        else if (type == float.class || type == Float.class) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get(name), Float.parseFloat(value), Float.parseFloat(value2));
        }
        else if (type == BigDecimal.class) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get(name), BigDecimal.valueOf(Double.parseDouble(value)), BigDecimal.valueOf(Double.parseDouble(value2)));
        }
        else if (type == double.class || type == Double.class) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get(name), Double.parseDouble(value), Double.parseDouble(value2));
        }
        else if (type == Date.class) {
            return (root, query, criteriaBuilder) ->
            {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    return criteriaBuilder.between(root.get(name), dateFormat.parse(value), dateFormat.parse(value2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException();
                }
            };
        }
        throw new IllegalArgumentException();
    }


}
