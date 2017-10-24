package dev.intell.business;

import java.util.List;
import java.util.ArrayList;

import dev.intell.utils.Util;
import dev.intell.models.*;


public class BackwardChaining implements Chaining{

    /**
     *
     */
    private Util util = new Util();
    /**
     * returns all releasable rules
     * @param rules
     * @param goal
     * @return List<Rule>
     */
    public List<Rule> releasableRules(List<String> goal,  List<Rule> rules) {
        List<Rule> butInRules = new ArrayList<>();
        for(int r = 0; r < rules.size(); r++ ){
            List<String> conc = rules.get( r ).getConclusions();
            /**
             * pick up rules whom conclusions contains
             * the goal
             */
            if ( util.bcContains( goal, conc ) ){
                butInRules.add( rules.get( r ) );
            }
        }
        return butInRules;
    }
    /**
     * returns weather BackwardChaining is successful or not
     * @param goal
     * @param rules
     * @param fact
     * @return boolean
     */
    public Result verify(List<String>goal, List<Rule> rules, Fact fact, String choice){
            //if all  rules.conclusions doesn't contain goal return false
            StringBuilder output = new StringBuilder();
            Result result = new Result();
            StringBuilder input = new StringBuilder();
            StringBuilder process = new StringBuilder();
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
            result.setInput(input.toString());
            boolean test = false;

            List<String> hypos = fact.getHypothesis();
            if(util.bcContains(goal,hypos )){
                process.append("Comparing ");
                if(goal.size() >1){
                    for (int i=0; i<goal.size()-1; i++) {
                        process.append(goal.get(i))
                                .append(", ");
                    }
                    process.append(goal.get(goal.size()-1));
                }else {
                    process.append(goal.get(0));
                }
                process.append("with List of Facts:\n")
                        .append(fact)
                        .append("\n");
                result.setText(process.toString());
                result.setOutput("===Goal Found===");
                return result;
            }else{
                List<Rule> butInRules = new ArrayList<Rule>();
                butInRules = releasableRules(goal, rules);
                while ( !test &&  butInRules.size() != 0){
                        for(int reg = 0; reg < butInRules.size(); reg++ ){
                            List<String> pre = butInRules.get(reg).getPremises();
                            List<String> fac = fact.getHypothesis();
                            process.append("Comparing Premises : ")
                                    .append(pre)
                                    .append(" of ")
                                    .append(butInRules.get(reg))
                                    .append("with hypothesis :")
                                    .append(fac)
                                    .append("\n");
                            if (util.allExists(pre, fac ) == true) {

                                test = true;
                            } else{
                                //delete rules
                                goal = util.diffLists(butInRules.get(reg).getPremises(), fact.getHypothesis());

                                for(int r = 0; r < util.delRegle(rules, butInRules.get(reg)).size(); r++ ){
                                    List<String> conc = util.delRegle(rules, butInRules.get(reg)).get( r ).getConclusions();
                                    /**
                                     * pick up rules whom conclusions contains
                                     * the goal
                                     */
                                    if ( util.bcContains( goal, conc ) ){
                                        butInRules.add( rules.get( r ) );
                                        test = true;
                                    }
                                }
                            }
                        }
                }

                if( !test ){
                    result.setText(process.toString());
                    result.setOutput(output.append("===Goal Not Found===").toString());
                    return result;
                }else{
                    result.setText(process.toString());
                    result.setOutput(output.append("===Goal Found===").toString());
                    return result;
                }
            }

    }

}