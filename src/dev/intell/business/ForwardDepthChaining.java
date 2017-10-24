package dev.intell.business;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;


public class ForwardDepthChaining{

//    private Util util = new Util();
//
//    /**
//     * returns all releasable rules
//     * @param regles
//     * @param hypothesis
//     * @return List<Rule>
//     */
//    public List<Rule> releasableRules(List<Rule> rules, List<String> hypothesis){
//        List<Rule> tmpSet = new ArrayList<Rule>();
//        for(Iterator<Rule> i = rules.iterator(); i.hasNext(); ) {
//            Rule rule = i.next();
//            if(util.allExists(rule.getPremises(), hypothesis) == true)
//                tmpSet.add(rule);
//        }
//        return tmpSet;
//    }
//    public Rule chosen (String choice, List<Rule> tmpRules, Fact fait){
//
//        Rule rule = new Rule();
//        //fifo
//        if(choice.equals("1")){
//            rule = tmpRules.get(0);
//        }else{
//            int max = 0, index = -1;
//            for(int i =0; i<tmpRules.size(); i++){
//                for(int j = 0; j< tmpRules.get(i).getConclusions().size(); j++){
//                    if((tmpRules.get(i).getPremises().size() > max) && (!fait.getHypothesis().contains("non" + tmpRules.get(i).getConclusions().get(j) ))){
//                        max = tmpRules.get(i).getPremises().size();
//                        index = i;
//                    }
//                }
//
//            }
//            rule = tmpRules.get(index);
//        }
//        return rule;
//    }
///*
//    //add rule.getConclusions() to hypothesis
//    //test before that !concl is not in hypothesis and that concl is not in hypothesis
//    */
//
//    /**
//     * returns weather ForwardChaining is succesful or not
//     * @param rules
//     * @param faits
//     * @param buts
//     * @return boolean
//     */
//    public boolean verify(List<String> buts, List<Rule>rules,Fact fait, String choice){
//
//
//        boolean res = false;
//        List<List<Rule>> tmpRules ;
//        List<Rule> _rules = new ArrayList<Rule>();
//        tmpRules = this.releasableRules(rules, fait.getHypothesis());
//        while((tmpRules.size() != 0)) {
//            int l = 0;
//            while((l < tmpRules.size()) && (util.allExists(buts, fait.getHypothesis()) == false)){
//                Rule rule = this.chosen(choice, tmpRules.subList(l,tmpRules.size()), fait);
//                List<String> concls = rule.getConclusions();
//                for (int j = 0; j < concls.size(); j++) {
//                    if (!fait.getHypothesis().contains(concls.get(j))) {
//                        fait.getHypothesis().add(concls.get(j));
//                    }
//                }
//                System.out.println("RULE        :\n"+rule);
//                l++;
//            }
//            System.out.println("     HYPOTHESIS        \n"+fait.getHypothesis());
//            for (int t = 0; t < tmpRules.size(); t++)
//                _rules = util.delRegle(rules, tmpRules.get(t));
//            tmpRules=this.releasableRules(_rules, fait.getHypothesis());
//
//        }
//        if(util.allExists(buts, fait.getHypothesis()) == true)
//            return true;
//        else
//            return false;
//
//    }

}


