
package by.bsuir.repository;

import by.bsuir.bean.Cargo;
import by.bsuir.bean.City;
import by.bsuir.bean.Company;
import by.bsuir.bean.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company getByName(String name);
    Company getById(int id);
    List<Company> findAll();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Company s WHERE s.id = ?1")
    void deleteCompanyById(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Company s set s.cargo = ?2, s.city = ?3, s.status = ?4 where s.id = ?1")
    void setNewData(int id, Cargo cargo, City city, Status status);

    @Query(value = "SELECT s FROM Company s WHERE s.name like %?1%")
    List<Company> searchCompanyByName(String name);

    @Query(value = "SELECT s FROM Company s WHERE s.id = ?1")
    List<Company> searchCompanyById(int id);

}

