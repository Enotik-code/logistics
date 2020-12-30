
package by.bsuir.repository;

import by.bsuir.bean.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    List<Status> findAll();
    Status findByName(String name);
}

