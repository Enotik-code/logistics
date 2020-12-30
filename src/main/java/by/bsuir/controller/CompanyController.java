
package by.bsuir.controller;

import by.bsuir.bean.Company;
import by.bsuir.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CargoRepository cargoRepository;

    @GetMapping(value = "/list")
    public ModelAndView listPage() {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/searchName")
    public ModelAndView searchCompanyByName(@ModelAttribute(name = "name") String name) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.searchCompanyByName(name));
        return modelAndView;
    }

    @GetMapping(value = "/searchId")
    public ModelAndView searchCompanyById(@ModelAttribute(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.searchCompanyById(id));
        return modelAndView;
    }


    @GetMapping(value = "/addCompany")
    public ModelAndView addCompanyPage() {
        ModelAndView modelAndView = new ModelAndView("addCompany");
        modelAndView.addObject("cityList", cityRepository.findAll());
        modelAndView.addObject("statusList", statusRepository.findAll());
        modelAndView.addObject("cargoList", cargoRepository.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/delete" + "/{id}")
    public ModelAndView deleteCompany(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        companyRepository.deleteCompanyById(id);
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }

    @GetMapping(value = "/update" + "/{id}")
    public ModelAndView updateCompany(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("/updateCompany");
        modelAndView.addObject("cityList", cityRepository.findAll());
        modelAndView.addObject("statusList", statusRepository.findAll());
        modelAndView.addObject("cargoList", cargoRepository.findAll());
        return modelAndView;
    }

    @PostMapping(value = "/update" + "/{id}")
    public ModelAndView addCompanyPagePost(@PathVariable(name = "id") int id,
                                           @RequestParam(value = "city", required = false) String city,
                                           @RequestParam(value = "cargo", required = false) String cargo,
                                           @RequestParam(value = "status", required = false) String status) {
        ModelAndView modelAndView = new ModelAndView("/updateCompany");
        companyRepository.setNewData(id, cargoRepository.findByName(cargo), cityRepository.findByName(city), statusRepository.findByName(status));
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }


    @PostMapping(value = "/addCompany")
    public ModelAndView addCompanyPagePost(@RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "cargo", required = false) String cargo,
                                           @RequestParam(value = "status", required = false) String status,
                                           @RequestParam(value = "city", required = false) String city) {
        ModelAndView modelAndView = new ModelAndView("addCompany");
        Company company = new Company(name, cityRepository.findByName(city), statusRepository.findByName(status), cargoRepository.findByName(cargo));
        companyRepository.save(company);
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }
}

