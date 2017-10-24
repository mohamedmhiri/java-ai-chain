package dev.intell.models;
import java.util.List;
import java.lang.StringBuilder;

public class Rule {
  private List<String> premises;
  private List<String> conclusions;
  private int number;

  public List<String> getPremises(){
    return this.premises;
  }
  public List<String> getConclusions(){
    return this.conclusions;
  }
  public int getNumber(){
    return this.number;
  }

  public void setPremises(List<String> premises){
    this.premises = premises;
  }


  public void setConclusions (List<String> conclusions){
    this.conclusions = conclusions;
  }

  public void setNumber(int number){
    this.number = number;
  }
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    return sb.append("Rule { number : ").append(this.getNumber())
          .append("  premises : ").append(this.getPremises())
          .append(" conclusions : ").append(this.getConclusions())
          .append("}")
          .toString();
  }
}
