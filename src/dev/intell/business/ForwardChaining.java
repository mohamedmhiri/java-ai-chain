package dev.intell.business;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import dev.intell.utils.Util;
import dev.intell.models.*;


public class ForwardChaining implements Chaining{

    private Util util = new Util();

    /**
     * returns all releasable rules
     * @param rules
     * @param hypothesis
     * @return List<Rule>
     */
    public List<Rule> releasableRules(List<Rule> rules, List<String> hypothesis){
        List<Rule> tmpSet = new ArrayList<Rule>();
        for(Iterator<Rule> i = rules.iterator(); i.hasNext(); ) {
            Rule rule = i.next();
            if(util.allExists(rule.getPremises(), hypothesis) == true)
                tmpSet.add(rule);
        }
        return tmpSet;
    }

    /**
     * returns weather ForwardChaining is succesful or not
     * @param rules
     * @param fact
     * @param goal
     * @return boolean
     */
    public Result verify(List<String> goal, List<Rule>rules, Fact fact, String choice){
        StringBuilder output = new StringBuilder();
        Result result = new Result();
        StringBuilder process = new StringBuilder();
        List<Rule> tmpRules ;
        List<Rule> _rules = new ArrayList<Rule>();
        //tmpRules est la liste des regles declenchables
        tmpRules = this.releasableRules(rules, fact.getHypothesis());

        while((tmpRules.size() != 0)) {
            int l = 0;
            process.append("List of Rules to be processed : \n");
            for(int r = 0; r < tmpRules.size(); r++ ) {
                process.append(tmpRules.get(r)).append("\n");
            }
            process.append("\nComparing ");
            while((l < tmpRules.size()) && (util.allExists(goal, fact.getHypothesis()) == false)){
                Rule rule = this.util.chosen(choice, tmpRules.subList(l,tmpRules.size()), fact);
                List<String> concls = rule.getConclusions();
                for (int j = 0; j < concls.size(); j++) {
                    if (!fact.getHypothesis().contains(concls.get(j))) {
                        fact.getHypothesis().add(concls.get(j));
                    }
                }
                process.append(rule)
                        .append("\n");
                l++;
            }
            process.append("with HYPOTHESIS")
                    .append(fact.getHypothesis())
                    .append("\n");

            for (int t = 0; t < tmpRules.size(); t++)
                _rules = util.delRegle(rules, tmpRules.get(t));
            tmpRules=this.releasableRules(_rules, fact.getHypothesis());
        }
        if(util.allExists(goal, fact.getHypothesis()) == true){
            result.setText(process.toString());
            result.setOutput(output.append("===Goal Found===").toString());
            return result;
        }
        else{
            result.setText(process.toString());
            result.setOutput(output.append("===Goal Not Found===").toString());
            return result;
        }

    }

}
