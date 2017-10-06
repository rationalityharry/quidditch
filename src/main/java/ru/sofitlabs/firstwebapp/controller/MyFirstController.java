package ru.sofitlabs.firstwebapp.controller;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import ru.sofitlabs.firstwebapp.data.TestEntity;
import ru.sofitlabs.firstwebapp.data.TestEntityService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class MyFirstController {

    @Autowired
    private TestEntityService testEntityService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam(name = "text") final String text) {
        final TestEntity testEntity = new TestEntity();
        testEntity.setText(text);
        testEntity.setAnotherNumber(5);
        testEntity.setNumber(1);
        testEntityService.add(testEntity);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "page", method = GET)
    public ModelAndView viewAll() {
        return new ModelAndView("index.html");
    }


    @RequestMapping(value = "view", method = GET)
    @ResponseBody
    public String getAll() {
        return testEntityService.getAll().toString();
    }



}
