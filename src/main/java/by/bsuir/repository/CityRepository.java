
package by.bsuir.repository;

import by.bsuir.bean.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findAll();
    City findByName(String name);
}

