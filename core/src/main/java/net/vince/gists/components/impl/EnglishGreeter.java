package net.vince.gists.components.impl;

import net.vince.gists.components.Greeter;
import org.springframework.stereotype.Component;

@Component
public class EnglishGreeter implements Greeter {

  @Override
  public String sayHi() {
    return "Hi";
  }
}
