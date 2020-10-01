package com.bindschaedel.Controller;

import com.bindschaedel.Service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/")
    public String index(Map<String, Object> model){
        model.put("clubs",clubService.getAll());
        return "index";
    }

    @PostMapping("/updateOrder")
    @ResponseStatus(value=HttpStatus.OK)
    public void updateOrder(@RequestParam(value="order[]") String[] order){
        for(int i = 0; i<order.length;i++){
            clubService.updateOrder(i,Integer.parseInt(order[i]));
        }
    }

    @PostMapping("/create")
    public String create(@RequestParam(value="name") String name, @RequestParam(value="date") String date,
                         @RequestParam(value="number") Integer number, @RequestParam(value="topic") String topic,
                         @RequestParam(value="order") Integer order,
                         Map<String,Object> model){
        model.put("clubs",clubService.create(name,date,number,topic,order));
        return "index";
    }
}
