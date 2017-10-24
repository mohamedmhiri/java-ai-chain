package dev.intell.business;

import dev.intell.models.Fact;
import dev.intell.models.Result;
import dev.intell.models.Rule;

import java.util.List;


public interface Chaining {
    Result verify(List<String> but, List<Rule> rules, Fact fact, String choice);
}
