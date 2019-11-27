package sample;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping
    @RequestMapping("/delete")
    public JsonView Delete(
            @RequestParam(value = "id") long id) {
        long result = _customerRepository.deleteById(id);

        ModelAndView mv = new ModelAndView("jsonView");
        mv.addObject("result", result);

        return (JsonView) mv;
    }






}
