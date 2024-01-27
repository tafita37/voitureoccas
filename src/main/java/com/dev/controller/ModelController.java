package com.dev.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dev.model.models.Model;
import com.dev.model.models.model.ModelGet;
import com.dev.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/model")
@CrossOrigin("*")
public class ModelController {
    private final ModelService modelService;

    @GetMapping(path = "/allModel", produces = "application/json")
    public List<Model> getAllModel() {
        return modelService.findAllModel();
    }

    @PostMapping("/insertModel")
    public Model insertModel(@RequestBody ModelGet modelGet) {
        return modelService.save(modelGet);
    }

    @PostMapping("/updateModel")
    public Model updateModel(@RequestParam int idModel, @RequestParam String nomModel, @RequestParam double vitesse, @RequestParam int idTransmission, @RequestParam Date dateSortie, @RequestParam int idMarque) {
        return modelService.update(idModel, nomModel, vitesse, idTransmission, dateSortie, idMarque);
    }

    @GetMapping("/findModelById")
    public Model findModelById(@RequestParam int idModel) {
        return modelService.findModelById(idModel).get();
    }

    @GetMapping("/deleteModel")
    public void deleteModel(@RequestParam int idModel) {
        modelService.delete(idModel);
    }
}
