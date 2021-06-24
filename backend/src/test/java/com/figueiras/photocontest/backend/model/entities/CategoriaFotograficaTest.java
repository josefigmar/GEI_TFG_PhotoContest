package com.figueiras.photocontest.backend.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.figueiras.photocontest.backend.model.entities.CategoriaFotografica;
import org.springframework.boot.test.context.SpringBootTest;

public class CategoriaFotograficaTest {

    @Test
    public void createInstanceTest()
    {
        CategoriaFotografica categoria = new CategoriaFotografica();

        Assertions.assertNotNull(categoria);
    }

    @Test
    public void setNameTest()
    {
        CategoriaFotografica categoria = new CategoriaFotografica();
        String name = "name";

        categoria.setNombreCategoria(name);

        Assertions.assertEquals(categoria.getNombreCategoria(), name);
    }

    @Test
    public void setDescriptionTest()
    {
        CategoriaFotografica categoria = new CategoriaFotografica();
        String desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris malesuada leo at tellus" +
                        "feugiat molestie. Mauris dapibus, ipsum vitae malesuada vulputate, velit arcu accumsan" +
                        "augue, nec molestie velit eros ut nunc. Fusce augue purus, porta sit amet est eget," +
                        "scelerisque sodales enim. In cursus molestie tempus. Duis eros purus, porta vel neque" +
                        "eu, gravida pharetra risus. Maecenas purus lectus, viverra quis metus lobortis, fermentum";
        categoria.setNombreCategoria(desc);

        Assertions.assertEquals(categoria.getNombreCategoria(), desc);
    }

}
