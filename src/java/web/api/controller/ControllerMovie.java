/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.api.controller;

import com.google.gson.Gson;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import web.api.daoimp.DaoImpMovie;
import web.api.model.Movies;

/**
 *
 * @author User
 */
public class ControllerMovie {

    @Autowired
    DaoImpMovie dao;
    Gson gson = new Gson();
    JSONObject json = new JSONObject();

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAll() {
        List list = dao.getAll();
        return gson.toJson(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSiswa(@PathVariable("id") int id) {
        Movies movie = dao.getMovie(id);
        return gson.toJson(movie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable("id") int id) {
        int status = dao.deleteMovie(id);
        json.put("status", status);
        return json.toString();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody String param) {
        Movies movie = new Gson().fromJson(param, Movies.class);
        int status = dao.insertMovie(movie);
        json.put("status", status);
        return json.toString();
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String update(@RequestBody String param) {
        Movies movie = new Gson().fromJson(param, Movies.class);
        int status = dao.updateMovie(movie);
        json.put("status", status);
        return json.toString();
    }

}
