package dev.intell.utils;

import dev.intell.business.BackwardChaining;
import dev.intell.business.Chaining;
import dev.intell.business.ForwardChaining;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import dev.intell.models.*;

import java.io.File;




public class Menu{

    private static final String RESULT = "/home/mohamed/Documents/development/java/ArtificialIntelligence/chain/src/dev/intell/results/result.txt";

    public void menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Glad to serve you !!\nSo, have you decided which type of chaining to use? \n    1) backward chaining\n    2) forward chaining)");
        //String type = sc.next("((F|f)or|(B|b)ack)ward((c|C)haining){0,1}");
        int type = sc.nextInt();;
        while ((type < 1 )|| (type > 2)){
            System.out.println("Invalid argument, please enter a valid choice");
            type = sc.nextInt();
        }
        System.out.println("Nice choice :D");
        File f =new File("/home/mohamed/Documents/development/java/ArtificialIntelligence/chain/");
        int len =(f.list().length -4)/2;
        System.out.println("Select a couple of rule and fact files");
        for (int i=1;i<=len;i++){
            System.out.println("    "+i+") ruleBase"+i);
        }
        int numR = sc.nextInt();
        while ((numR < 1 )|| (numR > len)){
            System.out.println("Invalid argument, please enter a valid choice");
            numR = sc.nextInt();
        }
        for (int i=1;i<=len;i++){
            System.out.println("    "+i+") factBase"+i);
        }
        int numF = sc.nextInt();
        while ((numF < 1 )|| (numF > len)){
            System.out.println("Invalid argument, please enter a valid choice");
            numF = sc.nextInt();
        }
        String rulePath = "/home/mohamed/Documents/development/java/ArtificialIntelligence/chain/ruleBase"+numR+".txt";
        File file = new File(rulePath);
        String factPath = "/home/mohamed/Documents/development/java/ArtificialIntelligence/chain/factBase"+numF+".txt";
        File file2 = new File(factPath);
//        if(file.exists() && !file.isDirectory()) {
//
//        }
        //
        Chaining chaining ;
        Binder bdr = new Binder();
        List<Rule> rules = bdr.bindRule( rulePath );
        List<Fact> fact = bdr.bindFact( factPath );
        int fNum =0;
        if(numF ==2){
            System.out.println("Chose between");
            System.out.println("1) "+fact.get(0));
            System.out.println("2) "+fact.get(1));
            fNum = sc.nextInt();
            while ((fNum < 1 )|| (fNum > 2)){
                System.out.println("Invalid argument, please enter a valid choice");
                fNum = sc.nextInt();
            }
        }
     //   List<String> words = bdr.bindBut( factPath );
        System.out.printf("give goal\n");
        String word = sc.next();
        List<String> goal = new ArrayList<String>();
        goal.add(word);
        if(type == 1){
            chaining = new BackwardChaining();

        }else{
            chaining = new ForwardChaining();
        }
//        System.out.println("Goals :");
//        for ( String re : words )
//            System.out.println( re );
//        StringBuilder result = new StringBuilder();
//        result.append("Goals :");
//        for ( String re : words )
//            result.append( re );
        StringBuilder input = new StringBuilder();
        Result result = new Result();
        input.append("Knowledge Base :\nList of Rules:\n");
        for (Rule rule: rules) {
            input.append(rule)
                    .append("\n");
        }
        input.append("List of Facts:\n")
                .append(fact)
                .append("\n")
                .append("Given Goal: ");
        if(goal.size() >1){
            for (int i=0; i<goal.size()-1; i++) {
                input.append(goal.get(i))
                        .append(", ");
            }
            input.append(goal.get(goal.size()-1));
        }else {
            input.append(goal.get(0));
        }

        input.append("\nProgress:\n");
        result=chaining.verify( goal, rules, fact.get( fNum ), "1" );
        result.setInput(input.toString());

        //result.append(res);
        System.out.println("Done!!!\n1) Save the result in a text file !\n2) Get the input here !");
        int num1 =sc.nextInt();
        while ((num1 < 1 )|| (num1 > 2)){
            System.out.println("Invalid argument, please enter a valid choice");
            num1 = sc.nextInt();
        }
        if(num1 == 1){
            BufferedWriter bw = null;
            FileWriter fw = null;

            try {

                fw = new FileWriter(RESULT);
                bw = new BufferedWriter(fw);
                bw.write(result.toString());

//                    System.out.println("Done");

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                try {

                    if (bw != null)
                        bw.close();

                    if (fw != null)
                        fw.close();

                } catch (IOException ex) {

                    ex.printStackTrace();

                }

            }
        }else{
            System.out.println(result.toString());
        }
        System.out.println("We hope that you are satisfied\nPeace be unto you !!");
//        if(res)
//            System.out.println( "\n\n===================== but atteint =====================" );
//        else
//            System.out.println( "\n\n===================== but non atteint =====================" );

        /*for( Rule r : rules )
          System.out.println( r );
        for( Fact f : facts )
          System.out.println( f );*/


        //System.out.println( rules.get(0).getPremises().get(0) instanceof String );
        /*List<String> res = bc.bcContains( words, rules.get(0).getPremises() );*/

    }

}

