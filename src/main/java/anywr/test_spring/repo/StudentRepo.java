package anywr.test_spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import anywr.test_spring.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer>,JpaSpecificationExecutor<Student>{

}
