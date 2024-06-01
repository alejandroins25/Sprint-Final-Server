package cat.institutmarianao.gymwebserver.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.gymwebserver.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class UserWithDni implements Specification<User> {

	private static final long serialVersionUID = 1L;
	private String dni;

	public UserWithDni(String dni) {
		this.dni = dni;
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (dni == null) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
		}
		return criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" + dni.toLowerCase() + "%");
	}

}
