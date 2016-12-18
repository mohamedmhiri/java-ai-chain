package dev.intell.business;

import dev.intell.models.Fait;
import dev.intell.models.Regle;

import java.util.List;


public interface Chaining {
    StringBuilder verify(List<String> but, List<Regle> regles, Fait fait, String choice);
}
