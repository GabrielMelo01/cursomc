package com.gm_digital.cursomc.resources;

import com.gm_digital.cursomc.domain.Categoria;
import com.gm_digital.cursomc.domain.Produto;
import com.gm_digital.cursomc.dto.CategoriaDTO;
import com.gm_digital.cursomc.dto.ProdutoDTO;
import com.gm_digital.cursomc.resources.utils.URL;
import com.gm_digital.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping(value ="/{id}")
    @ResponseBody
    public ResponseEntity<Optional<?>> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }


}
