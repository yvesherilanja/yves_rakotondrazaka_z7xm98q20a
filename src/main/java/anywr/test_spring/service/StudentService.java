package anywr.test_spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import anywr.test_spring.model.Student;
import anywr.test_spring.pagination.Paged;
import anywr.test_spring.pagination.Paging;
import anywr.test_spring.repo.StudentRepo;
@Service
public class StudentService {

	@Autowired
	private StudentRepo sr ;
	
	public Paged<Student> getAllStudent(int pageNumber, int size){
		PageRequest request = PageRequest.of(pageNumber - 1, size);
		Page<Student> list =sr.findAll(request);
		return new Paged<>(list, Paging.of(list.getTotalPages(), pageNumber, size));
	}
	
	private Page<Student> students_filter(String class_name, String teacher_name, Pageable pageable) {
	    Page<Student> results = sr.findAll(new Specification<Student>() {
	        private static final long serialVersionUID = 1L;

	        @Override
	        public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
	            List<Predicate> predicates = new ArrayList<>();

	            if (!class_name.trim().equals("")) {
	                predicates.add(cb.and(cb.equal(cb.lower(root.get("classe").get("name")),
	                        class_name.trim().toLowerCase())));
	            }

	            if (!teacher_name.trim().equals("")) {
	                String[] names = teacher_name.trim().split("\\s+");
	                List<Predicate> namePredicates = new ArrayList<>();

	                for (String name : names) {
	                    Expression<String> firstNameExpression = cb.lower(root.get("classe").get("teacher").get("first_name"));
	                    Expression<String> lastNameExpression = cb.lower(root.get("classe").get("teacher").get("last_name"));

	                    Predicate firstNamePredicate = cb.like(firstNameExpression, "%" + name.toLowerCase() + "%");
	                    Predicate lastNamePredicate = cb.like(lastNameExpression, "%" + name.toLowerCase() + "%");
	                    Predicate namePredicate = cb.or(firstNamePredicate, lastNamePredicate);

	                    for (int i = 1; i < name.length(); i++) {
	                        firstNameExpression = cb.concat(firstNameExpression, " ");
	                        firstNameExpression = cb.concat(firstNameExpression,
	                                cb.lower(root.get("classe").get("teacher").get("first_name")));
	                        lastNameExpression = cb.concat(lastNameExpression, " ");
	                        lastNameExpression = cb.concat(lastNameExpression,
	                                cb.lower(root.get("classe").get("teacher").get("last_name")));

	                        firstNamePredicate = cb.and(firstNamePredicate,
	                                cb.like(firstNameExpression, "%" + name.toLowerCase() + "%"));
	                        lastNamePredicate = cb.and(lastNamePredicate,
	                                cb.like(lastNameExpression, "%" + name.toLowerCase() + "%"));
	                        namePredicate = cb.or(firstNamePredicate, lastNamePredicate);
	                    }
	                    namePredicates.add(namePredicate);
	                }
	                Predicate teacherNamePredicate = cb.and(namePredicates.toArray(new Predicate[namePredicates.size()]));
	                predicates.add(teacherNamePredicate);
	            }
	            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	        }

	    }, pageable);
	    return results;
	}
	public Paged<Student> getAllStudentFilter(String class_name,String teacher_name,int pageNumber, int size){
		PageRequest request = PageRequest.of(pageNumber - 1, size);
		Page<Student> list =this.students_filter(class_name, teacher_name, request);
		return new Paged<>(list, Paging.of(list.getTotalPages(), pageNumber, size));
	}
}
