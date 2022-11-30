package bg.softuni.xmlProccessing.repositories;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.CustomerWithSalesDto;
import bg.softuni.xmlProccessing.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByOrderByBirthDateAscIsYoungDriverDesc();

    @Query("select new bg.softuni.xmlProccessing.domain.dtos.exportDto.CustomerWithSalesDto( " +
            "c.name, count(s), avg(p.price * s.discount)) "+
            "from Customer c " +
            "join c.sales  s " +
            "join s.car ca " +
            "join ca.parts p " +
            "group by c " +
            "order by count(s) desc , avg(p.price * s.discount) desc")
    List<CustomerWithSalesDto> findCustomerWithSales();

}