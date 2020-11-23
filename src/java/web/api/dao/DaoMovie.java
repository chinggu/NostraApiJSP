/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.api.dao;

import java.util.List;
import web.api.model.Movies;
/**
 *
 * @author User
 */
public interface DaoMovie {

    public List getAll();

    public Movies getMovie(int id);

    public int insertMovie(Movies movie);

    public int updateMovie(Movies movie);

    public int deleteMovie(int idMovie);
}
