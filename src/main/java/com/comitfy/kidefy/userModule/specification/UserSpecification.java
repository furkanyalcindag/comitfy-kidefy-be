package com.comitfy.kidefy.userModule.specification;

import com.comitfy.kidefy.userModule.entity.Role;
import com.comitfy.kidefy.userModule.entity.User;
import com.comitfy.kidefy.util.common.BaseSpecification;
import com.comitfy.kidefy.util.common.SearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification extends BaseSpecification<User> {


    private List<SearchCriteria> criterias;

    public List<SearchCriteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<SearchCriteria> criterias) {
        this.criterias = criterias;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : criterias) {
            Predicate predicate = null;

            if (criteria.getOperation().equalsIgnoreCase(":")) {
                if (criteria.getKey().startsWith("roles.")) {
                    Join<User, Role> roles = root.join("roles", JoinType.INNER);
                    String roleField = criteria.getKey().replace("roles.", "");
                    if (roles.get(roleField).getJavaType() == String.class) {
                        predicate = builder.like(
                                builder.lower(roles.get(roleField)), "%" + criteria.getValue().toString().toLowerCase() + "%");
                    } else {
                        predicate = builder.equal(roles.get(roleField), criteria.getValue());
                    }
                } else if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    predicate = builder.like(
                            builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
                } else {
                    predicate = builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            }


            if (predicate != null) {
                predicates.add(predicate);
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }



}