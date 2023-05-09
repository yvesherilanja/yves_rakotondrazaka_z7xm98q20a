package anywr.test_spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import anywr.test_spring.model.Classe;

@Repository
public interface ClasseRepo extends JpaRepository<Classe,Integer>{

}
