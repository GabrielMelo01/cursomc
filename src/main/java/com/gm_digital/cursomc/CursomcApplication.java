package com.gm_digital.cursomc;

import com.gm_digital.cursomc.domain.*;
import com.gm_digital.cursomc.domain.enums.EstadoPagamento;
import com.gm_digital.cursomc.domain.enums.TipoCliente;
import com.gm_digital.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
