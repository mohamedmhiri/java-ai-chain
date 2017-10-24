package dev.intell.utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import dev.intell.models.*;

/**
 * contains all methods used by Backward
 * and Forward Chaining
 */
public class Util{
    //returns all elements of words not existing in _set
    public List<String> diffLists( List<String> words, List<String> _set){
        int nb = 0;
        List<String> rest = new ArrayList<String>();
        for( int w =0; w< words.size() ; w++ ){
            int n = nb;
            //System.out.println( words.get( w ) );
            if( ! ( _set.contains( words.get( w ) ) ) ){
                nb++;
            }
            if( nb != n )
                rest.add( words.get( w ) );

        }
        return rest;
    }
    /**
     * tests weather all  words(goal) items are in
     * _set (rules.conclusions) or not
     * @param words
     * @param set
     * @return boolean
     */
    public boolean bcContains( List<String> words, List<String> set){
        int nb = 0, n=0;
        boolean rest = false;
        for( int w =0; w< words.size() ; w++ ){
//            n = nb;
//            for(int j=0;j<set.size();j++){
//                if(  !( set.get(j).equals( words.get( w ) ) ) ){
//                    nb++;
//                }
//            }
            if(!set.contains(words.get(w)))
                nb++;
        }
        if( nb == n )
            rest = true;
        return rest;
    }

    /**
     * tests weather all list1 values exist in list2
     * @param list1
     * @param list2
     * @return boolean
     */
    public boolean allExists( List<String>list1, List<String>list2 ){
        int nb = list1.size();
        boolean rest = false;
        for( int w =0; w< list1.size() ; w++ ){
            //System.out.println( words.get( w ) );
            if(  list2.contains( list1.get( w ) ) ){
                nb--;
            }
            if( nb == 0 )
                rest = true;

        }
        return rest;
    }

    /**
     * returns rules - rule
     * @param rules
     * @param rule
     * @return List<Rule>
     */
    public List<Rule> delRegle(List<Rule> rules, Rule rule){
        for (Iterator<Rule> iter = rules.listIterator(); iter.hasNext(); ) {
            Rule r = iter.next();
            if ( r.getNumber() == rule.getNumber() ) {
                iter.remove();
            }
        }
        return rules;
    }

    public int arraySum(int[]tab){
        int res=0;
        for (int i=0; i<tab.length;i++)
            res+=tab[i];
        return res;
    }


    /**
     * returns a Rule from a set of Rules
     *
     * @param choice
     * @param tmpRules
     * @param fact
     * @return
     */
    public Rule chosen (String choice, List<Rule> tmpRules, Fact fact){

        Rule rule = new Rule();
        //fifo
        if(choice.equals("1")){
            rule = tmpRules.get(0);
        }else{
            int max = 0, index = -1;
            for(int i =0; i<tmpRules.size(); i++){
                for(int j = 0; j< tmpRules.get(i).getConclusions().size(); j++){
                    if((tmpRules.get(i).getPremises().size() > max) && (!fact.getHypothesis().contains("non" + tmpRules.get(i).getConclusions().get(j) ))){
                        max = tmpRules.get(i).getPremises().size();
                        index = i;
                    }
                }

            }
            rule = tmpRules.get(index);
        }
        return rule;
    }
}