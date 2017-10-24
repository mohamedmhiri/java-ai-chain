package dev.intell.utils;
import java.io.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import dev.intell.models.*;


public class Binder{

    public List<Rule> bindRule(String path){
        
        try (BufferedReader in =  new BufferedReader(new FileReader( path ))) {
            String line;
            List<Rule> rules = new ArrayList<Rule>();
            while((line = in.readLine()) != null) {
                Rule rule =new Rule();
                String _line=line.substring(line.lastIndexOf(":") + 1);
                String _nbr = line.substring(1,line.indexOf(":"));
                int nbr = Integer.parseInt(_nbr);
                //test if "ET" is in premises
                String prepremises=_line.substring(0,_line.indexOf("=>"));
                List<String> premises = new ArrayList<String>();
                if(prepremises.toLowerCase().contains("ET".toLowerCase())){
                    String[] pre = prepremises.split("ET");
                    for (String p: pre){
                        //if( !p.toLowerCase().contains("NON".toLowerCase()) ){
                            String pp = p.replace(" ","");
                            premises.add(pp);
                        //}

                    }
                }
                else{
                    premises.add(prepremises);
                }
                    
                //test if "ET" is in conclusions
                //if()
                String preconclusions=_line.substring(_line.lastIndexOf(">") + 1);
                List<String> conclusions = new ArrayList<String>();
                if(preconclusions.toLowerCase().contains("ET".toLowerCase())){
                    String[] pre = preconclusions.split("ET");
                    for (String p: pre){
                        String pp = p.replace(" ","");
                        conclusions.add(pp);
                    }
                }
                else{
                    conclusions.add(preconclusions);
                }
                rule.setNumber(nbr);
                rule.setPremises( premises );
                rule.setConclusions( conclusions );
                rules.add(rule);

            }
            return rules;
        } 
        catch (IOException e) {
            e.printStackTrace();
            return null;       
        }
    }


    public List<Fact> bindFact(String path){
        try (BufferedReader in =  new BufferedReader(new FileReader( path ))) {
            String line;
            List<Fact> facts = new ArrayList<Fact>();
            while( ( ( line = in.readLine() ) != null ) && !( line.toLowerCase().contains( "BUT".toLowerCase() ) ) ) {
                //System.out.println( line );
                List<String> hypothesis = new ArrayList<String>();
                Fact fact = new Fact();
                if(line.contains(",")){
                    String[] pre = line.split(",");
                    for (String p: pre){
                        hypothesis.add(p);
                    }
                }
                else{
                    hypothesis.add(line);
                }
                fact.setHypothesis( hypothesis );
                facts.add(fact);
            }
            return facts;
        } 
        catch (IOException e) {
            System.out.println("error" + e.getMessage());
            return null;
        }
    }

}


