package howl.in.disguise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransformerRepository extends JpaRepository<Transformer, Long>{


}
