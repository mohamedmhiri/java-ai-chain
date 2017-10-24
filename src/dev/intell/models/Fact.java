package dev.intell.models;
import java.util.List;

public class Fact {
  public List<String> hypothesis;

  public List<String> getHypothesis(){
    return this.hypothesis;
  }

  public void setHypothesis (List<String> hypothesis){
    this.hypothesis = hypothesis;
  }
  
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    return sb.append("Fact { hypothesis : ").append(this.getHypothesis())
          .append("}")
          .toString();
  }
}
