package com.caiolopes.apirestful;

import com.caiolopes.apirestful.util.importar.ImportadorDeCSV;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class ApirestfulApplication implements CommandLineRunner {

    private final ImportadorDeCSV i;

    public static void main(String[] args) {
        SpringApplication.run(ApirestfulApplication.class, args);
    }

    @Override
    public void run(String... args) {

        String csv = "notas.csv";

        try {
            i.importarCSV(csv);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}