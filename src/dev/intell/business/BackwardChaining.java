package dev.intell.business;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import dev.intell.utils.Util;
import dev.intell.models.*;


public class BackwardChaining implements Chaining{

    /**
     *
     */
    private Util util = new Util();

    /**
     * returns weather BackwardChaining is succesful or not
     * @param but
     * @param regles
     * @param fait
     * @return boolean
     */
    public StringBuilder verify(List<String>but, List<Regle>regles,Fait fait, String choice){
  //if all  regles.conclusions doesn't contain but return false
            StringBuilder str = new StringBuilder();
            if(util.bcContains(but, fait.getHypothesis()))
                return str;
            boolean test = false;
            List<Regle> butInRegles = new ArrayList<Regle>();
            for( int r = 0; r < regles.size(); r++ ){
                if ( util.bcContains( but, regles.get( r ).getConclusions() ) == true ){
                    butInRegles.add( regles.get( r ) );
                    test = true;
                    //System.out.println( "1 ");
                }
            }
            if( test == false ){
                //System.out.println( "2 ");
                return str;

            }
            else{
                //else
//System.out.println( "3 ");
                for( int reg =0;  reg < butInRegles.size(); reg++ )
                    if (util.allExists(butInRegles.get(reg).getPremisses(), fait.getHypothesis()) == true) {
                        System.out.println("premisses " + butInRegles.get(reg).getPremisses() + " hypothesis " + /*fait.getHypothesis()*/butInRegles.get(reg).getConclusions());
                        System.out.println(butInRegles.get(reg));
                        return str;
                    } else /*if ( this.bcContains(but, butInRegles.get( reg ).getConclusions() ).size() == 0 )*/ {
                        System.out.println("premisses " + butInRegles.get(reg).getPremisses() + " but " + /*but*/butInRegles.get(reg).getConclusions());
                        System.out.println(butInRegles.get(reg));
                        //delete regles
                        str.append(this.verify(util.diffLists(butInRegles.get(reg).getPremisses(), fait.getHypothesis()), util.delRegle(regles, butInRegles.get(reg)), fait, choice));
                        //System.out.println( this.bcContains( butInRegles.get( reg ).getPremisses(), fait.getHypothesis() ) );
                    }

            }
            return str;
    }
}