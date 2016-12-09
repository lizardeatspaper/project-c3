package cz.project.c3.repository.user;

import cz.project.c3.domain.user.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
    Optional<Company> findOneByName(String name);
}
