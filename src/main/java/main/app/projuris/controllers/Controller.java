package main.app.projuris.controllers;

import br.com.projuris.*;
import com.br.projuris.MyFindChar;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @PostMapping("arrays/{array}/{subArray}")
    public int arrays(
            @PathVariable("array") String array,
            @PathVariable("subArray") String subArray) {

        String[] a1 = array.replace("[","").replace("]","").split(",");
        String[] a2 = subArray.replace("[","").replace("]","").split(",");
        int[] arr1 = new int[a1.length];
        int[] arr2 = new int[a2.length];

        for(int i = 0; i < arr1.length; i++){
            arr1[i] = Integer.parseInt(a1[i]);
        }
        for(int i = 0; i < arr2.length; i++){
            arr2[i] = Integer.parseInt(a2[i]);
        }

        MyFindArray m = new MyFindArray();
        return m.findArray(arr1,arr2);
    }

    @PostMapping("char/{word}")
    public int chars(
            @PathVariable("word") String word) {
        MyFindChar m = new MyFindChar();
        return m.findChar(word);
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        String json = "";

        if (file.isEmpty()) {
            return json;
        }

        try {
            List<Funcionario> fun = new ArrayList<>();
            MyCalculo m = new MyCalculo();

            final Path path = Files.createTempFile("funcionarios", ".txt");
            byte[] buf = file.getBytes();
            Files.write(path, buf);

            BufferedReader reader = new BufferedReader(new FileReader(new File(String.valueOf(path))));

            String line = reader.readLine();
            while(line != null){
                String[] parts = line.split(" ");
                parts[0] = new String(parts[0].getBytes(StandardCharsets.ISO_8859_1));
                parts[1] = new String(parts[1].getBytes(StandardCharsets.ISO_8859_1));
                Funcionario f = new Funcionario(parts[0],parts[1], BigDecimal.valueOf(Double.parseDouble(parts[2])));
                fun.add(f);
                line = reader.readLine();
            }

            List<CustoCargo> listaCargo = m.custoPorCargo(fun);
            List<CustoDepartamento> listaDepartamento = m.custoPorDepartamento(fun);

            ObjectMapper mapper = new ObjectMapper();

            json = mapper.writeValueAsString(listaCargo) + mapper.writeValueAsString(listaDepartamento);

        } catch (IOException e) {
            System.out.println("Arquivo invalido");
            e.printStackTrace();
        }

        return json;
    }

}
