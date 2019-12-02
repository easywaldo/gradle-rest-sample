package sample;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerRepository _customerRepository;

    @GetMapping
    @RequestMapping("/add")
    public Customer Add(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName) {

        Customer customer = new Customer(firstName, lastName);

        _customerRepository.save(customer);

        return customer;

    }

    @GetMapping
    @RequestMapping("/search")
    public Customer Search(
            @RequestParam(value = "id") long id) {

        Customer result = _customerRepository.findById(id);

        return result;

    }

    @GetMapping
    @RequestMapping("/all")
    public Iterable<Customer> All() {
        return _customerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("/update")
    public Customer Update(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName) {

        Customer result = _customerRepository.findById(id);
        result.updateName(firstName, lastName);
        return _customerRepository.save(result);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> Delete(
            @RequestParam(value = "id") long id) {
        Customer result = _customerRepository.findById(id);
        if(result != null) {
            _customerRepository.deleteById(id);
        }
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }






}
