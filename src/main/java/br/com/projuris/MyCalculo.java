package br.com.projuris;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyCalculo implements Calculo {


    @Override
    public List<CustoCargo> custoPorCargo(List<Funcionario> funcionarios) {

        List<CustoCargo> lcc = new ArrayList<>();

        Map<String,BigDecimal> map = new HashMap<>();

        List<String> cargos = funcionarios.stream().map(Funcionario::getCargo).distinct().collect(Collectors.toList());
        for(String c : cargos){
            map.put(c,new BigDecimal(0));
        }
        for(Funcionario f : funcionarios){
            map.put(f.getCargo(),map.get(f.getCargo()).add(f.getSalario()));
        }
        for(String c : cargos){
            CustoCargo cc = new CustoCargo();
            cc.setCargo(c);
            cc.setCusto(map.get(c));
            lcc.add(cc);
        }

        return lcc;
    }

    @Override
    public List<CustoDepartamento> custoPorDepartamento(List<Funcionario> funcionarios) {

        List<CustoDepartamento> lcd = new ArrayList<>();

        Map<String,BigDecimal> map = new HashMap<>();

        List<String> departamentos = funcionarios.stream().map(Funcionario::getDepartamento).distinct().collect(Collectors.toList());
        for(String c : departamentos){
            map.put(c,new BigDecimal(0));
        }
        for(Funcionario f : funcionarios){
            map.put(f.getDepartamento(),map.get(f.getDepartamento()).add(f.getSalario()));
        }
        for(String c : departamentos){
            CustoDepartamento cd = new CustoDepartamento();
            cd.setDepartamento(c);
            cd.setCusto(map.get(c));
            lcd.add(cd);
        }

        return lcd;
    }
}
